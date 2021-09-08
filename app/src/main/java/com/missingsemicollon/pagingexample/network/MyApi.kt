package com.missingsemicollon.pagingexample.network

import com.missingsemicollon.pagingexample.PassengersResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): PassengersResponse


    companion object {

        private const val BASE_URL = "https://api.instantwebtools.net/v1/"

        operator fun invoke(): MyApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().also {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    it.addInterceptor(logging)
                }.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)

    }
}