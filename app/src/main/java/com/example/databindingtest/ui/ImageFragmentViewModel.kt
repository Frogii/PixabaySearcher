package com.example.databindingtest.ui

import androidx.lifecycle.ViewModel
import com.example.databindingtest.utils.SingleLiveEvent

class ImageFragmentViewModel() : ViewModel() {

    private var imageUrl: String? = null

    private val imageFragmentEvent = SingleLiveEvent<String>()

    fun setImageClickSingleEvent() {
        imageFragmentEvent.value = "image_click"
    }

    fun getImageClickSingleEvent() = imageFragmentEvent

    fun onInit(url: String?) {
        imageUrl = url
    }

    fun getUrl() = imageUrl
}