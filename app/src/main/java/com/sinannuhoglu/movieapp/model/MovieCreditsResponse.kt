package com.sinannuhoglu.movieapp.model


import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast?>?,
    @SerializedName("crew")
    val crew: List<Crew?>?,
    @SerializedName("id")
    val id: Int?
)