package com.example.databindingtest.retrofit.model

data class PixaPhoto(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)