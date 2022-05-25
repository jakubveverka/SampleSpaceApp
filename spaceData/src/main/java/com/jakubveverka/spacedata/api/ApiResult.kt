package com.jakubveverka.spacedata.api

sealed class ApiResult<out  T> {
    abstract val data: T
    abstract val message: String?

    data class Success<out R>(override val data: R): ApiResult<R>() {
        override val message: String? = null
    }

    data class Error<out R>(private val errorMessage: String?, private val oldData: R): ApiResult<R>() {
        override val data = oldData
        override val message: String? = errorMessage
    }

    data class Loading<out R>(private val oldData: R): ApiResult<R>() {
        override val data = oldData
        override val message: String? = null
    }
}