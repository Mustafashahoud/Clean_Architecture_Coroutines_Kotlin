package com.android.mustafa.core.domain.base

import com.android.mustafa.core.errorHandling.Failure


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any>(
    val data: T? = null,
    val error: Failure? = null
) {
    class Success<out T : Any>(data: T) : Result<T>(data)
    class Error(failure: Failure = Failure.UnknownError) : Result<Nothing>(error = failure)
}
