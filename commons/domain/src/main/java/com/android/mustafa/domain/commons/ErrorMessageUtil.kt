package com.android.mustafa.domain.commons

import android.content.Context

/**
 * Is used in data binding "activity/fragment", never in ViewModel.
 */
object ErrorMessageUtil {
    @JvmStatic
    fun getErrorMessage(context: Context, error: ErrorEntity?): String {
        return when (error) {
            is ErrorEntity.NetworkError -> context.resources.getString(R.string.network_error_message)
            is ErrorEntity.ServerError -> context.resources.getString(R.string.server_error_message)
            is ErrorEntity.UnknownError -> context.resources.getString(R.string.unknown_error_message)
            else -> context.resources.getString(R.string.unknown_error_message)
        }
    }
}
