package com.example.databindingtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ImageFragmentViewModelFactory(private val url: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageFragmentViewModel(url) as T
    }
}