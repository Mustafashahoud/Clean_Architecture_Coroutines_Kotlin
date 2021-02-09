package com.android.mustafa.core.errorHandling

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GeneralErrorHandler @Inject constructor() {
    fun getError(throwable: Throwable): Failure {
        return when (throwable) {
            is IOException -> Failure.NetworkError
            is HttpException -> Failure.ServerError
            else -> Failure.UnknownError
            //Other errors can be added
        }
    }
}