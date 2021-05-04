package com.example.databindingtest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databindingtest.retrofit.RetrofitInstance
import com.example.databindingtest.retrofit.model.PixaPhoto
import com.example.databindingtest.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MainViewModel() : ViewModel() {

    init {
        getPhotos()
    }

    private val _photos: MutableLiveData<Response<PixaPhoto>> = MutableLiveData()
    val photos: LiveData<Response<PixaPhoto>>
        get() = _photos

    private val recyclerEvent = SingleLiveEvent<String>()

    fun setSingleRecyclerEvent(url: String) {
        recyclerEvent.value = url
    }

    fun getSingleRecyclerEvent() = recyclerEvent

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPhotos("bananas")
                if (response.isSuccessful) {
                    _photos.postValue(response)
                    Log.d("myLog", "successful response")
                }
            } catch (e: Exception) {
                Log.d("myLog", e.message.toString())
            }
        }
    }
}