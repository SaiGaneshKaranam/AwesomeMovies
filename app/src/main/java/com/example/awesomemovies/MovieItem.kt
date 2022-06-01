package com.example.awesomemovies

import com.google.gson.annotations.SerializedName


data class  MovieItem(
    @SerializedName("Title") val title: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,


)
