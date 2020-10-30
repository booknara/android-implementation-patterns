package com.booknara.android.apps.repository

import com.booknara.android.apps.database.dao.QuoteDao
import com.booknara.android.apps.database.entity.Quote
import com.booknara.android.apps.patterns.model.Resource
import com.booknara.android.apps.patterns.network.ApiInterface
import com.booknara.android.apps.patterns.network.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class QuoteRepository(
        private val apiInterface: ApiInterface,
        private val quoteDao: QuoteDao
) {
    @ExperimentalCoroutinesApi
    fun getRandomQuote(): Flow<Resource<Quote>> {
        return networkBoundResource(
                fetchFromLocal = { quoteDao.getQuote() },
                shouldFetchFromRemote = { true },
                fetchFromRemote = { apiInterface.getRandomQuote() },
                processRemoteResponse = { },
                saveRemoteData = { quoteDao.insertOrUpdateQuote(it) },
                onFetchFailed = { _, _ -> }
        ).flowOn(Dispatchers.IO)
    }
}