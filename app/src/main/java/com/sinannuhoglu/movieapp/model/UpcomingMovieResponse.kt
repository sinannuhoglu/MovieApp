package com.sinannuhoglu.movieapp.model


import com.google.gson.annotations.SerializedName

data class UpcomingMovieResponse(
    @SerializedName("dates")
    val dates: Dates?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val upcomingMovieItem: List<MovieItem>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)