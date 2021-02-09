package com.android.mustafa.core.errorHandling

import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import com.android.mustafa.core.extension.connectivityManager
import javax.inject.Inject
import javax.inject.Singleton


// Source: https://github.com/android10/Android-CleanArchitecture-Kotlin
/**
 * Injectable class which returns information about the network connection state.
 */
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.connectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network= connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}