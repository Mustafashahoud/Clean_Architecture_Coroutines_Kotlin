package com.android.mustafa.dynamicfeatures.movies.ui

import com.android.mustafa.core.errorHandling.Failure
import com.android.mustafa.domain.commons.ErrorEntity


// A generic class that contains data and status about loading this data.
sealed class Resource<out T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val error: ErrorEntity? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Loading : Resource<Nothing>()
    class Error(error: ErrorEntity?) : Resource<Nothing>(error = error)
}
