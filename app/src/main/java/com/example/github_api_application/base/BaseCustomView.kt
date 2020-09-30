package com.example.github_api_application.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleableRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class BaseCustomView<VIEW_DATA_BINDING: ViewDataBinding>(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {

    protected lateinit var binding: VIEW_DATA_BINDING

    init {
        getAttrs(attrs, defStyleAttr)
        initView()
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int, defStyleRes: Int = 0) {

    }

    private fun initView() {

    }

    abstract fun setTypedArray(typedArray: TypedArray)

    @LayoutRes
    abstract fun getLayoutID() : Int

    @StyleableRes
    abstract fun getCustomViewStyle() : IntArray

    fun setLifeCycleOwner(owner: LifecycleOwner) {
        binding.lifecycleOwner = owner
    }

    protected fun binding(action : VIEW_DATA_BINDING.() -> Unit) {
        binding.run (action)
    }

}