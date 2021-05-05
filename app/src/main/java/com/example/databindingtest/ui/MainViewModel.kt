package com.example.databindingtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.databindingtest.repository.PixaRepository
import com.example.databindingtest.utils.SingleLiveEvent

class MainViewModel(private val repository: PixaRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    private val recyclerEvent = SingleLiveEvent<String>()

    fun setSingleRecyclerEvent(url: String) {
        recyclerEvent.value = url
    }

    fun getSingleRecyclerEvent() = recyclerEvent

    fun searchPhotos(searchString: String) {
        currentQuery.value = searchString
    }

    val photos = currentQuery.switchMap { string ->
        repository.getSearchResults(string)
    }

    companion object {
        private const val DEFAULT_QUERY = "bananas"
    }
}