//package com.android.mustafa.core.util
//
//import com.android.mustafa.domain.commons.ErrorEntity
//import com.android.mustafa.domain.commons.EitherResult
//import retrofit2.Response
//
//suspend fun <T, R : Any> safeApiCall(
//    networkHandler: NetworkHandler,
//    apiCall: suspend () -> Response<T>,
//    transform: (T) -> R,
//    default: T
//): EitherResult<R> {
//    return when (networkHandler.isNetworkAvailable()) {
//        true -> request(apiCall, transform, default)
//        false -> EitherResult.Failure(ErrorEntity.NetworkError)
//    }
//}
//
//suspend fun <T, R : Any> request(
//    apiCall: suspend () -> Response<T>,
//    transform: (T) -> R,
//    default: T
//): EitherResult<R> {
//    return try {
//        val response = apiCall.invoke()
//        when (response.isSuccessful) {
//            true -> EitherResult.Success(transform((response.body() ?: default)))
//            false -> EitherResult.Failure(ErrorEntity.ServerError)
//        }
//    } catch (exception: Throwable) {
//        EitherResult.Failure(ErrorEntity.ServerError)
//    }
//}
