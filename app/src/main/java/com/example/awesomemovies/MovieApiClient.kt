package com.example.awesomemovies

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiClient {

    companion object {

        fun getRetrofit(): Retrofit {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true).build()
            val create = GsonConverterFactory.create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(create)
                .client(okHttpClient)
                .build()

            return retrofit
        }

    }
}