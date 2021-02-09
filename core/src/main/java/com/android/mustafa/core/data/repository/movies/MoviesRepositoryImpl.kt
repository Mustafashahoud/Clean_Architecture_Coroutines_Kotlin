package com.android.mustafa.core.data.repository.movies

import androidx.room.withTransaction
import com.android.mustafa.core.data.database.CleanArchDatabase
import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.preference.PreferencesHelper
import com.android.mustafa.core.data.repository.movies.source.local.MoviesCacheDataSource
import com.android.mustafa.core.data.repository.movies.source.remote.MoviesRemoteDataSource
import com.android.mustafa.core.domain.base.Result
import com.android.mustafa.core.domain.movies.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesCacheDataSource: MoviesCacheDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val preference: PreferencesHelper,
    private val db: CleanArchDatabase
) : MoviesRepository {

    // TODO this is super ugly, make smth like https://github.com/Mustafashahoud/MoviesApp/blob/coroutines-flow/app/src/main/java/com/mustafa/movieguideapp/repository/NetworkBoundRepository.kt
    override suspend fun getMovies(page: Int): Result<List<Movie>> {
        //if it is not expired get it from cache
        if (!moviesCacheDataSource.isCacheExpiredOrNotExist(page)) {
            return moviesCacheDataSource.getMoviesForPages((1..page).toList())
        }

        // Or fetch it from network
        val movies: Result<List<Movie>> = moviesRemoteDataSource.fetchMovies(page)

        prepareData(page, movies.data)

        db.withTransaction {
            //clear in case Expiration
            moviesCacheDataSource.clearMovies(page)

            //Save the new data
            moviesCacheDataSource.saveMoviesPerPage(page, movies.data ?: emptyList()).also {
                preference.setMoviesPageLastCacheTime(page.toString(), System.currentTimeMillis())
            }
        }

        return moviesCacheDataSource.getMoviesForPages((1..page).toList())
    }

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
        private const val TMDB_API_STARTING_PAGE_INDEX = 1
        private const val TMDB_API_PAGE_SIZE = 20
    }

    override suspend fun getMovies(): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

}