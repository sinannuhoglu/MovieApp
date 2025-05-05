package com.sinannuhoglu.movieapp.ui.Cast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinannuhoglu.movieapp.model.Cast
import com.sinannuhoglu.movieapp.network.ApiClient
import com.sinannuhoglu.movieapp.util.Constants
import kotlinx.coroutines.launch

class CastViewModel: ViewModel() {
    val castList: MutableLiveData<List<Cast?>?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    val isLoading =  MutableLiveData(false)

    fun getMovieCredits(movieId: Int) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieCredits(movieId.toString(), Constants.BEARER_TOKEN)
                if (response.isSuccessful) {
                    val list = response.body()?.cast
                    castList.value = list
                } else {
                    errorMessage.value = response.message()
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
            isLoading.value = false
            }
        }
    }
}