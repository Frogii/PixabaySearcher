package com.example.databindingtest.retrofit

import com.example.databindingtest.BuildConfig
import com.example.databindingtest.retrofit.model.PixaPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaAPI {

    @GET("/api/?image_type=photo")
    suspend fun getPhotos(
        @Query("q") searchString: String,
        @Query("page") page: Int,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<PixaPhoto>
}