package com.example.databindingtest.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
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

@BindingAdapter("bind:visible")
fun setVisible(view: View, boolean: Boolean) {
    view.isVisible = boolean
}