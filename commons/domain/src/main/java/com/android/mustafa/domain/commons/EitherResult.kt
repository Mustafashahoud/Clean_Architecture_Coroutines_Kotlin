package com.android.mustafa.domain.commons

sealed class EitherResult<out T> {
    data class Success<T>(val data: T? = null) : EitherResult<T>()
    data class Failure(val errorEntity: ErrorEntity? = null, val throwable: Throwable? = null) :
        EitherResult<Nothing>()
}
