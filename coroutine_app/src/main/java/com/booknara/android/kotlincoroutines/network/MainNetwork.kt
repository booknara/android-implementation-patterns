package com.booknara.android.kotlincoroutines.network

import com.booknara.android.kotlincoroutines.util.SkipNetworkInterceptor
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val service: MainNetwork by lazy {
  val okHttpClient = OkHttpClient.Builder()
    //.certificatePinner(getCertificatePinner())
    .addInterceptor(SkipNetworkInterceptor())
    .addInterceptor(getHttpLoggingInterceptor())
    .build()

  val retrofit = Retrofit.Builder()
    .baseUrl("http://localhost/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  retrofit.create(MainNetwork::class.java)
}

private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
  level = HttpLoggingInterceptor.Level.BODY
}

private fun getCertificatePinner() = CertificatePinner.Builder()
  .add(
    "www.secureexample.com",
    "sha256/7HIpactkIAq2Y49orFOOQKurWxmmSFZhBCoQYcRhJ3Y="
  ).build()

fun getNetworkService() = service

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface MainNetwork {
  @GET("next_title.json")
  suspend fun fetchNextTitle(): String
}


