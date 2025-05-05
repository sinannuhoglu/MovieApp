package com.sinannuhoglu.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinannuhoglu.movieapp.model.MovieItem
import com.sinannuhoglu.movieapp.network.ApiClient
import com.sinannuhoglu.movieapp.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val upcomingMovieList: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val nowPlayingList: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    init {
        getMovieList()
        getUpComingMovie()
    }

    fun getMovieList() {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieList(token = Constants.BEARER_TOKEN)

                if (response.isSuccessful) {
                    nowPlayingList.postValue(response.body()?.nowPlayingItems)
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

    fun getUpComingMovie() {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getUpComingMovie(token = Constants.BEARER_TOKEN)

                if (response.isSuccessful) {
                    upcomingMovieList.postValue(response.body()?.upcomingMovieItem)
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
}