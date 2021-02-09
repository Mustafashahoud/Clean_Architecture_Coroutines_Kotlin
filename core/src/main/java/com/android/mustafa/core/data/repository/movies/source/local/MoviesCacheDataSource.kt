package com.android.mustafa.core.data.repository.movies.source.local


import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.database.movies.MovieDao
import com.android.mustafa.core.data.database.util.query
import com.android.mustafa.core.data.preference.PreferencesHelper
import com.android.mustafa.core.domain.base.Result
import javax.inject.Inject


class MoviesCacheDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val preferencesHelper: PreferencesHelper
) {

    companion object {
        const val EXPIRATION_TIME = (60 * 10 * 1000).toLong() // 5 minutes
    }

    suspend fun clearMovies(page: Int) {
        return movieDao.deleteMoviesPerPage(page)
    }

    suspend fun clearMovies() {
        return movieDao.deleteAll()
    }

    suspend fun saveMoviesPerPage(page: Int, movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            movieDao.insertMovies(movies)
            setLastCacheTime(page, System.currentTimeMillis())
        }
    }

    suspend fun getAllMovies(): Result<List<Movie>> {
        return query {
            movieDao.getAllMovies()
        }
    }

    suspend fun getMoviesForPages(pages: List<Int>): Result<List<Movie>> {
        return query {
            movieDao.getMoviesForPages(pages)
        }
    }

    suspend fun getMoviesPerPage(page: Int): Result<List<Movie>> {
        return query {
            movieDao.getMoviesPerPage(page)
        }
    }

    private fun setLastCacheTime(key: Int, lastCache: Long) {
        preferencesHelper.setMoviesPageLastCacheTime(key.toString(), lastCache)
    }

    fun isCacheExpiredOrNotExist(key: Int): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = preferencesHelper.getMoviesPerPageLastCacheTime(key.toString())

        // No cache for a chart with this timeSpan key
        if (lastUpdateTime == PreferencesHelper.DEFAULT_VALUE) {
            return true
        }
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }


}