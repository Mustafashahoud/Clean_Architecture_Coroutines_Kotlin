package com.android.mustafa.dynamicfeatures.movies.ui

import com.android.mustafa.core.errorHandling.Failure


// A generic class that contains data and status about loading this data.
sealed class Resource<out T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val error: Failure? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Loading : Resource<Nothing>()
    class Error(error: Failure?) : Resource<Nothing>(error = error)
}
