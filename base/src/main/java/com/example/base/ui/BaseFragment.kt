package com.example.base.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.base.BR

abstract class BaseFragment<VIEW_DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>(
    @LayoutRes layoutRes: Int, viewModelCls: Class<VIEW_MODEL>) : Fragment(layoutRes) {

    protected val viewModel by viewModel(clazz = viewModelCls.kotlin)
    protected lateinit var binding: VIEW_DATA_BINDING

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.bind(requireView())!!
        binding {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
    }

    protected fun binding(action: VIEW_DATA_BINDING.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VIEW_MODEL.() -> Unit) {
        viewModel.run(action)
    }

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { data ->
            data?.let(action)
        })
    }
}