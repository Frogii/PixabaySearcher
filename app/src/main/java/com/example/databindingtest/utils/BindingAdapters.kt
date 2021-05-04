package com.example.databindingtest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    Glide
        .with(imageView.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}