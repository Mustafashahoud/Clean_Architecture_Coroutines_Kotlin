package com.android.mustafa.core.domain.movies.repository

import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.domain.base.Result

interface MoviesRepository {

    suspend fun getMovies(): Result<List<Movie>>

    suspend fun getMovies(page: Int): Result<List<Movie>>

    suspend fun clearMovies()

    suspend fun saveMovies(movies: List<Movie>)

}