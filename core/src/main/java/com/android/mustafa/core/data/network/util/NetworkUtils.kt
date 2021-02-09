package com.android.mustafa.core.data.network.util

import retrofit2.Call
import com.android.mustafa.core.domain.base.Result
import com.android.mustafa.core.errorHandling.Failure


fun <T, R : Any> request(call: Call<T>, transform: (T) -> R, default: T): Result<R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Result.Success(transform((response.body() ?: default)))
            false -> Result.Error(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        Result.Error(Failure.ServerError)
    }
}