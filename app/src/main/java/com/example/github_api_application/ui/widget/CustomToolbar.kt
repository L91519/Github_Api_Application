package com.example.github_api_application.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.example.github_api_application.R
import com.example.github_api_application.databinding.IncludeToolbarBinding

class CustomToolbar : FrameLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
        getAttributes(attrs, defStyleAttr)
    }

    @LayoutRes
    var toolbarLayout = R.layout.include_toolbar
    lateinit var binding: IncludeToolbarBinding

    var navigateToBackListener: OnClickListener? = null

    var title: String? = ""
        set(value) {
            field = value
            binding.titleTextView.text = value
        }

    private fun initView() {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(toolbarLayout, this@CustomToolbar, false)
        addView(view)
        binding = DataBindingUtil.bind(view)!!
    }

    private fun getAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int = 0) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, defStyleAttr, defStyleRes)
        title = typedArray.getString(R.styleable.CustomToolbar_toolbar_title)
        binding.navigationButton.setOnClickListener { navigateToBackListener?.onClick(binding.navigationButton) }
        typedArray.recycle()
    }
}