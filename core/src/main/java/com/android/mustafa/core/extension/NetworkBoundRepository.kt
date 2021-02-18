package com.android.mustafa.core.extension

import kotlinx.coroutines.CoroutineDispatcher

abstract class networkBoundResource<ResultType, RequestType>(
    loadFromDb: suspend () -> ResultType,
    fetchFromNetwork: suspend () -> RequestType,
    saveFetchResult: suspend (RequestType) -> Unit,
    shouldFetch: () -> Boolean = { true },
    pagingChecker: (RequestType) -> Boolean = { true },
    dispatcherIO: CoroutineDispatcher
) {
    init {
        if (!shouldFetch()) {
//            loadFromDb()
        }
    }
}