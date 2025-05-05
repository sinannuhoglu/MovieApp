package com.sinannuhoglu.movieapp.network

import com.sinannuhoglu.movieapp.model.MovieCreditsResponse
import com.sinannuhoglu.movieapp.model.MovieDetailResponse
import com.sinannuhoglu.movieapp.model.NowPlayingResponse
import com.sinannuhoglu.movieapp.model.UpcomingMovieResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("now_playing")
    suspend fun getMovieList(@Header("Authorization") token: String): Response<NowPlayingResponse>

    @GET("{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: String, @Header("Authorization") token: String) : Response<MovieDetailResponse>

    @GET("upcoming")
    suspend fun getUpComingMovie(@Header("Authorization") token: String): Response<UpcomingMovieResponse>

    @GET("{movieId}/credits") suspend fun getMovieCredits(@Path("movieId") movieId: String, @Header("Authorization") token: String): Response<MovieCreditsResponse>
}