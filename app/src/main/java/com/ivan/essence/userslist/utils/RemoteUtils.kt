package com.ivan.essence.userslist.utils

suspend fun <RESPONSE> createApiCall(call: suspend () -> RESPONSE): SuccessOrError<RESPONSE, Throwable> =
    try {
        val response = call()
        SuccessOrError.Success(response)
    } catch (e: Throwable) {
        SuccessOrError.Error(e)
    }