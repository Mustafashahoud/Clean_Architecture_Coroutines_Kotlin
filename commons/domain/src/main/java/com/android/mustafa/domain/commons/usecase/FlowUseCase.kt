package com.android.mustafa.domain.commons.usecase

import com.android.mustafa.domain.commons.EitherResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


abstract class FlowUseCase<R, in P : Any> {
    suspend operator fun invoke(parameters: P): Flow<EitherResult<R>> = execute(parameters)
        .catch { throwable ->
            emit(
                EitherResult.Failure(throwable = throwable)
            )
        }

    protected abstract suspend fun execute(parameters: P): Flow<EitherResult<R>>
}
