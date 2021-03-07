package com.android.mustafa.core.domain.movies.usecase

import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.domain.movies.repository.MoviesRepository
import com.android.mustafa.domain.commons.usecase.FlowUseCase
import com.android.mustafa.domain.commons.EitherResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovies @Inject constructor(private val repository: MoviesRepository) :
    FlowUseCase<List<Movie>, Int>() {
    override suspend fun execute(parameters: Int): Flow<EitherResult<List<Movie>>> {
        return repository.getMovies(parameters)
    }
}