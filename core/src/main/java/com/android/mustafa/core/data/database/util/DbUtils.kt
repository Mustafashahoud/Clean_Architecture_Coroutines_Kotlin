package com.android.mustafa.core.data.database.util

import com.android.mustafa.core.errorHandling.Failure
import com.android.mustafa.core.domain.base.Result

suspend fun <T : Any> query(sqlQuery: suspend () -> T): Result<T> {
    return try {
        val queryResult = sqlQuery.invoke()
        Result.Success(queryResult)
    } catch (exception: Throwable) {
        Result.Error(Failure.CacheError)
    }
}