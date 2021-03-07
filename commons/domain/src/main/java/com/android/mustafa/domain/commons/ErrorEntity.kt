package com.android.mustafa.domain.commons

sealed class ErrorEntity {

    object NetworkError : ErrorEntity()

    object ServerError : ErrorEntity()

    object UnknownError : ErrorEntity()
}