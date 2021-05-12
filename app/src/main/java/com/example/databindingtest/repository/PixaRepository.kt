package com.example.databindingtest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.databindingtest.paging.PixaPagingSource
import com.example.databindingtest.retrofit.RetrofitInstance

class PixaRepository {

    private val api = RetrofitInstance.api

    fun getSearchResults(searchString: String) =
        Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ), pagingSourceFactory = { PixaPagingSource(api, searchString) }).liveData
}