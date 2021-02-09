package com.android.mustafa.core.domain.movies.usecase

import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.domain.base.Result
import com.android.mustafa.core.domain.base.UseCase
import com.android.mustafa.core.domain.movies.repository.MoviesRepository
import javax.inject.Inject

class GetMovies @Inject constructor(private val repository: MoviesRepository): UseCase<List<Movie>, Int>() {

    override suspend fun run(params: Int): Result<List<Movie>> {
        return repository.getMovies(params)
    }
}