package com.example.github_api_application.ui

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.github_api_application.generated.callback.OnClickListener
import com.example.github_api_application.ui.widget.CustomToolbar

object CustomToolbarBindingAdapter {
    @BindingAdapter("navigationClick")
    @JvmStatic
    fun setOnClickNavigation(view: CustomToolbar, listener: View.OnClickListener?) {
        view.navigateToBackListener = listener
    }
}
