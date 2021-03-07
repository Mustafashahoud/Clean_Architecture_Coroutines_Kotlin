package com.android.mustafa.core.util


import com.android.mustafa.domain.commons.EitherResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<T>(
    private val networkHandler: NetworkHandler
) {

    @ExperimentalCoroutinesApi
    fun asFlow(): Flow<EitherResult<T>> = flow {
        // check if should fetch data from remote or not
        if (shouldFetch()) {
            when (val remoteResponse = safeApiCall(networkHandler) { apiCall() }) {
                is EitherResult.Success -> {
                    remoteResponse.data?.let {
                        // Save the remoteResponse
                        saveFetchResult(remoteResponse.data!!)
                    }
                    emit(EitherResult.Success(localFetchAll()))
                }
                is EitherResult.Failure -> {
                    emit(EitherResult.Failure(remoteResponse.errorEntity, remoteResponse.throwable))
                }

            }
        } else {
            // load from cache
            emit(EitherResult.Success(data = localFetchAll()))
        }
    }

    abstract suspend fun apiCall(): T
    abstract suspend fun saveFetchResult(data: T)
    abstract suspend fun localFetch(): T
    abstract suspend fun localFetchAll(): T
    open fun shouldFetch() = true
}


