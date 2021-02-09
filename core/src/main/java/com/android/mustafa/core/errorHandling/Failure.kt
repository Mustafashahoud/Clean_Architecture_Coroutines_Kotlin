package com.android.mustafa.core.errorHandling

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkError : Failure()
    object ServerError : Failure()
    object CacheError : Failure()
    object UnknownError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}
