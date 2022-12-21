package com.jakubveverka.spacedata.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

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

inline fun <T, R: Any> Flow<ApiResult<List<T>>>.transformList(crossinline transform: suspend (value: List<T>) -> List<R>): Flow<ApiResult<List<R>>> =
    this.transform { value ->
        val transformedValue = transform(value.data)
        val newResult = when (value) {
            is ApiResult.Error -> ApiResult.Error(value.message, transformedValue)
            is ApiResult.Loading -> ApiResult.Loading(transformedValue)
            is ApiResult.Success -> ApiResult.Success(transformedValue)
        }
        return@transform emit(newResult)
    }