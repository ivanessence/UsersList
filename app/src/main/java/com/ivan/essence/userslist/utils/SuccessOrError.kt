package com.ivan.essence.userslist.utils

sealed class SuccessOrError<out SUCCESS, out ERROR> {
    data class Success<out SUCCESS>(val value: SUCCESS): SuccessOrError<SUCCESS, Nothing>()
    data class Error<out ERROR>(val value: ERROR): SuccessOrError<Nothing, ERROR>()
    object Loading: SuccessOrError<Nothing, Nothing>()

    fun <R> mapResult(mapper: (SUCCESS) -> R): SuccessOrError<R, ERROR> {
        return when (this) {
            is Success -> Success(mapper(value))
            is Error -> Error(value)
            is Loading -> Loading
        }
    }
}