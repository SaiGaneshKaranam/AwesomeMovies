package com.example.awesomemovies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/?apikey=4eb4c80")
    fun searchMovies(
        @Query("s") queryText: String,
        @Query("page") pageNumber: Int
    ): Call<SearchResult>
    @GET("/?apikey=4eb4c80")
    fun getData(
        @Query("i") id :String
    ): Call<MovieInDetail>
}