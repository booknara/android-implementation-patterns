package com.booknara.android.apps.patterns.network

import com.booknara.android.apps.database.entity.Quote
import com.booknara.android.apps.patterns.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiInterface {
    @GET("quotes/random")
    fun getRandomQuote(): Flow<ApiResponse<Quote>>
}