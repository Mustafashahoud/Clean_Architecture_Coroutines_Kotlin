package com.android.mustafa.core.domain.movies.repository

import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.domain.commons.EitherResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(page: Int): Flow<EitherResult<List<Movie>>>
}