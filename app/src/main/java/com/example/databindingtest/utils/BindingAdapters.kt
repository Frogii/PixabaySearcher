package com.example.databindingtest.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.databindingtest.R
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("bind:recyclerImageUrl")
fun loadRecyclerImage(imageView: ImageView, url: String) {
    Glide
        .with(imageView.context)
        .load(url)
        .placeholder(
            ResourcesCompat.getDrawable(
                imageView.resources,
                R.drawable.ic_image_placeholder,
                null
            )
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    val circularProgress = CircularProgressDrawable(imageView.context).apply {
        strokeWidth = 20f
        centerRadius = 80f
        setColorSchemeColors(ResourcesCompat.getColor(
                imageView.resources,
                R.color.progress_bar,
                null
            )
        )
        start()
    }
    val options = RequestOptions().placeholder(circularProgress)

    Glide
        .with(imageView.context)
        .load(url)
        .apply(options)
        .error(R.drawable.ic_image_placeholder)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Snackbar.make(imageView, "Failed to load image", Snackbar.LENGTH_SHORT).show()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("bind:visible")
fun setVisible(view: View, boolean: Boolean) {
    view.isVisible = boolean
}