package com.jakubveverka.spacedata.api

sealed class ApiResult<out  T>(val data: T? = null, val message: String? = null) {

    data class Success<out R>(val responseData: R?): ApiResult<R>(
        data = responseData
    )

    data class Error(val errorMessage: String? = null): ApiResult<Nothing>(
        data = null,
        message = errorMessage
    )

    object Loading: ApiResult<Nothing>()
}