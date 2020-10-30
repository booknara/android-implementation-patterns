package com.booknara.android.apps.patterns.model

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        private const val HTTP_SUCCESS_NO_CONTENT = 204

        // error response
        fun <T> create(error: Throwable) : ApiErrorResponse<T> {
            return ApiErrorResponse(
                    error.message ?: "Unknown error",
            0)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val headers = response.headers()
                if (body == null || response.code() == HTTP_SUCCESS_NO_CONTENT) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body, headers)
                }
            } else {
                val message = response.errorBody()?.string()
                val errorMsg = if (message.isNullOrEmpty()) {
                    response.message()
                } else {
                    message
                }
                ApiErrorResponse(errorMsg ?: "Unknown error",
                        response.code())
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
        val body: T?,
        val headers: okhttp3.Headers
): ApiResponse<T>()

data class ApiErrorResponse<T> (
        val message: String,
        val status: Int
): ApiResponse<T>()