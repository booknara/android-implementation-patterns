package com.booknara.android.apps.patterns.network

import com.booknara.android.apps.patterns.model.ApiErrorResponse
import com.booknara.android.apps.patterns.model.ApiResponse
import com.booknara.android.apps.patterns.model.ApiSuccessResponse
import com.booknara.android.apps.patterns.model.Resource
import kotlinx.coroutines.flow.*

inline fun <DB, REMOTE> networkBoundResource(
        crossinline fetchFromLocal: () -> Flow<DB>,
        crossinline shouldFetchFromRemote: (DB?) -> Boolean = { true },
        crossinline fetchFromRemote: () -> Flow<ApiResponse<REMOTE>>,
        crossinline processRemoteResponse: (response: ApiSuccessResponse<REMOTE>) -> Unit = { Unit },
        crossinline saveRemoteData: (REMOTE) -> Unit = { Unit },
        crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> Unit}
) = flow<Resource<DB>> {

    // start loading initially
    emit(Resource.loading(null))

    val localData = fetchFromLocal().first()

    if (shouldFetchFromRemote(localData)) {
        // show local data first for a while
        emit(Resource.loading(localData))

        fetchFromRemote().collect { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    processRemoteResponse(apiResponse)
                    apiResponse.body?.let {
                        saveRemoteData(it)
                    }
                    emitAll(fetchFromLocal().map { dbData ->
                        Resource.success(dbData)
                    })
                }

                is ApiErrorResponse -> {
                    onFetchFailed(apiResponse.message, apiResponse.status)
                    emitAll(fetchFromLocal().map {
                        Resource.error(apiResponse.message, it)
                    })
                }
            }
        }
    } else {
        emitAll(fetchFromLocal().map {
            Resource.success(it)
        })
    }
}