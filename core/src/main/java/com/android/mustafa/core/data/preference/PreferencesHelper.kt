package com.android.mustafa.core.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.android.mustafa.core.annotations.OpenForTesting
import javax.inject.Inject

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@OpenForTesting
class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        const val DEFAULT_VALUE = (-1).toLong()
        private const val PREF_BUFFER_PACKAGE_NAME = "com.android.mustafa.core.data.preference"
    }

    private val bufferPref: SharedPreferences =
        context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)

    /**
     * Get the value for a specific key
     * @key the key
     */
    fun getMoviesPerPageLastCacheTime(key: String): Long {
        return bufferPref.getLong(key, DEFAULT_VALUE)
    }

    /**
     * Store and retrieve the last time data was cached
     * @param key the key
     * @param value the value
     */
    fun setMoviesPageLastCacheTime(key: String, value: Long) {
        bufferPref.edit().putLong(key, value).apply()
    }

}
