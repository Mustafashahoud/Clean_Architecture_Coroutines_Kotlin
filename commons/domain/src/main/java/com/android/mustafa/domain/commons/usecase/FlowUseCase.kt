package com.android.mustafa.domain.commons.usecase

import com.android.mustafa.domain.commons.FailureData
import com.android.mustafa.domain.commons.util.NetworkCodes
import com.android.mustafa.domain.commons.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


abstract class FlowUseCase<in P: Any, R>() {
    suspend operator fun invoke(parameters: P): Flow<Resource<R>> = execute(parameters)
        .catch { throwable ->
            emit(
                Resource.Failure(
                    FailureData(
                        throwable = throwable,
                        message = throwable.localizedMessage
                    )
                )
            )
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}
