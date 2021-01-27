package com.example.base.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseWebViewFragment<VIEW_DATA_BINDING : ViewDataBinding>
    (@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected lateinit var binding: VIEW_DATA_BINDING

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.bind(requireView())!!
        binding {
            lifecycleOwner = viewLifecycleOwner
        }
        onViewCreated(savedInstanceState)
    }

    abstract fun onViewCreated(savedInstanceState: Bundle?)

    protected fun binding(action: VIEW_DATA_BINDING.() -> Unit) {
        binding.run(action)
    }
}