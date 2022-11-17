package com.ivan.essence.userslist.utils

sealed class SuccessOrError<out SUCCESS, out ERROR> {
    data class Success<out SUCCESS>(val value: SUCCESS): SuccessOrError<SUCCESS, Nothing>()
    data class Error<out ERROR>(val value: ERROR): SuccessOrError<Nothing, ERROR>()

    fun <R> mapSuccess(mapper: (SUCCESS) -> R): SuccessOrError<R, ERROR> {
        return when (this) {
            is Success -> Success(mapper(value))
            is Error -> Error(value)
        }
    }

    fun toSuccessOrError(): SuccessOrError<SUCCESS, ERROR> {
        return when (this) {
            is Success -> Success(value)
            is Error -> Error(value)
        }
    }
}