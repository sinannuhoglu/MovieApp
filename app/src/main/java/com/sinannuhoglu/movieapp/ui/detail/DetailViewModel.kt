package com.sinannuhoglu.movieapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinannuhoglu.movieapp.model.Cast
import com.sinannuhoglu.movieapp.model.MovieDetailResponse
import com.sinannuhoglu.movieapp.network.ApiClient
import com.sinannuhoglu.movieapp.util.Constants
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    val movieResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val castList: MutableLiveData<List<Cast?>?> = MutableLiveData()
    val isLoading =  MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getMovieDetail(movieId: Int) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieDetail(movieId = movieId.toString(), token = Constants.BEARER_TOKEN)

                if(response.isSuccessful) {
                    movieResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occured"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }

    fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieCredits(movieId.toString(), Constants.BEARER_TOKEN)
                if (response.isSuccessful) {
                    castList.value = response.body()?.cast
                } else {
                    errorMessage.value = response.message()
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}