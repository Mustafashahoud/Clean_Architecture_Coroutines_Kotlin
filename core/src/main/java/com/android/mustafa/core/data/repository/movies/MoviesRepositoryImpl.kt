package com.android.mustafa.core.data.repository.movies

import androidx.room.withTransaction
import com.android.mustafa.core.data.database.CleanArchDatabase
import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.preference.PreferencesHelper
import com.android.mustafa.core.data.repository.movies.source.local.MoviesCacheDataSource
import com.android.mustafa.core.data.repository.movies.source.remote.MoviesRemoteDataSource
import com.android.mustafa.core.domain.movies.repository.MoviesRepository
import com.android.mustafa.core.util.NetworkBoundResource
import com.android.mustafa.core.util.NetworkHandler
import com.android.mustafa.domain.commons.EitherResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val moviesCacheDataSource: MoviesCacheDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val networkHandler: NetworkHandler,
    private val preference: PreferencesHelper,
    private val db: CleanArchDatabase
) : MoviesRepository {
    private fun prepareData(page: Int, data: List<Movie>?) {
        if (!data.isNullOrEmpty()) {
            data.forEach { it.page = page }
            data.forEachIndexed { index, movie ->
                // Just a function to generate ids .. autoGenerate does not help in my case
                movie.id = (index + 1) + ((page - 1) * TMDB_API_PAGE_SIZE)
            }
        }
    }

    companion object {
        private const val TMDB_API_PAGE_SIZE = 20
    }

    @ExperimentalCoroutinesApi
    override suspend fun getMovies(page: Int): Flow<EitherResult<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>>(networkHandler = networkHandler) {
            override suspend fun apiCall(): List<Movie> {
                return moviesRemoteDataSource.fetchMovies(page)
            }

            override suspend fun saveFetchResult(data: List<Movie>) {
                prepareData(page, data)
                db.withTransaction {
                    //clear in case Expiration
                    moviesCacheDataSource.clearMovies(page)

                    //Save the new data
                    moviesCacheDataSource.saveMoviesPerPage(page, data).also {
                        preference.setMoviesPageLastCacheTime(
                            page.toString(),
                            System.currentTimeMillis()
                        )
                    }
                }
            }

            override suspend fun localFetch(): List<Movie> {
                return moviesCacheDataSource.getMoviesPerPage(page)
            }

            override fun shouldFetch(): Boolean {
                return moviesCacheDataSource.isCacheExpiredOrNotExist(page)
            }

            override suspend fun localFetchAll(): List<Movie> {
                return moviesCacheDataSource.getMoviesForPages((1..page).toList())
            }

        }.asFlow()

    }
}