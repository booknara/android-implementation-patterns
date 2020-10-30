package com.booknara.android.apps.patterns.model

data class Resource<out T> (
        val status: Status,
        val data: T?,
        val message: String?
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        // success
        fun<T> success(data: T?) : Resource<T> {
            return Resource(
                    Status.SUCCESS,
                    data,
                    null
            )
        }

        // error
        fun<T> error(message: String, data: T? = null) : Resource<T> {
            return Resource(
                    Status.ERROR,
                    data,
                    message
            )
        }

        // loading
        fun<T> loading(data: T? = null) : Resource<T> {
            return Resource(
                    Status.LOADING,
                    data,
                    null
            )
        }
    }
}