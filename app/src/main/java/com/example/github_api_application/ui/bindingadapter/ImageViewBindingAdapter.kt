package com.example.github_api_application.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object ImageViewBindingAdapter {
    @BindingAdapter("imageURL")
    @JvmStatic
    fun setImageURL(view: ImageView, url: String?) {
        url?.let {
            view.load(url)
        }
    }

}