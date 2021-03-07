package com.android.mustafa.core.util

import com.android.mustafa.domain.commons.EitherResult
import com.android.mustafa.domain.commons.ErrorEntity

suspend fun <T> safeApiCall(
    networkHandler: NetworkHandler,
    apiCall: suspend () -> T
): EitherResult<T> {
    return when (networkHandler.isNetworkAvailable()) {
        true -> request(apiCall)
        false -> EitherResult.Failure(ErrorEntity.NetworkError)
    }
}

suspend fun <T> request(
    apiCall: suspend () -> T
): EitherResult<T> {
    return try {
        val response = apiCall()
        EitherResult.Success(response)
    } catch (exception: Throwable) {
        EitherResult.Failure(ErrorEntity.ServerError, exception)
    }
}

/**
 * package com.android.mustafa.core.util

import com.android.mustafa.domain.commons.ErrorEntity
import com.android.mustafa.domain.commons.EitherResult
import retrofit2.Response

suspend fun <T> safeApiCall(
networkHandler: NetworkHandler,
apiCall: suspend () -> Response<T>
): EitherResult<T> {
return when (networkHandler.isNetworkAvailable()) {
true -> request(apiCall)
false -> EitherResult.Failure(ErrorEntity.NetworkError)
}
}

suspend fun <T> request(
apiCall: suspend () -> Response<T>
): EitherResult<T> {
return try {
val response = apiCall.invoke()
when (response.isSuccessful) {
true -> EitherResult.Success(response.body())
false -> EitherResult.Failure(ErrorEntity.ServerError)
}
} catch (exception: Throwable) {
EitherResult.Failure(ErrorEntity.ServerError)
}
}

 */
