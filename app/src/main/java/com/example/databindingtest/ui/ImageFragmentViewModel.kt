package com.example.databindingtest.ui

import androidx.lifecycle.ViewModel
import com.example.databindingtest.utils.SingleLiveEvent

class ImageFragmentViewModel(url: String) : ViewModel() {

    private var imageUrl: String? = null

    init {
        imageUrl = url
    }

    private val imageFragmentEvent = SingleLiveEvent<String>()

    fun setImageClickSingleEvent() {
        imageFragmentEvent.value = "image_click"
    }

    fun getImageClickSingleEvent() = imageFragmentEvent

    fun getUrl() = imageUrl
}