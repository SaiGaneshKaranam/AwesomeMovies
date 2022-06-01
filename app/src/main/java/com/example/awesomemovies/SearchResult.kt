package com.example.awesomemovies

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search") val search: List<MovieItem>
)


