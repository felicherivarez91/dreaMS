package com.healios.dreams.data

import retrofit2.Response

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ResponseWrapper<T> {
    companion object {
        fun <T> create(error: Throwable): ErrorResponseWrapper<T> {
            return ErrorResponseWrapper(
                error.message ?: "unknown error"
            )
        }

        fun <T> create(response: Response<T>): ResponseWrapper<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    EmptyResponseWrapper()
                } else {
                    SuccessResponseWrapper(body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ErrorResponseWrapper(
                    errorMsg ?: "unknown error"
                )
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class EmptyResponseWrapper<T> : ResponseWrapper<T>()

data class SuccessResponseWrapper<T>(
    val body: T
) : ResponseWrapper<T>() {
    constructor(body: T, linkHeader: String?) : this(
        body = body
    )
}

data class ErrorResponseWrapper<T>(val errorMessage: String) : ResponseWrapper<T>()
