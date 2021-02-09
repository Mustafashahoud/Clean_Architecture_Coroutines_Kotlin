package com.android.mustafa.core.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Source: https://github.com/android10/Android-CleanArchitecture-Kotlin
 */
/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }
}
