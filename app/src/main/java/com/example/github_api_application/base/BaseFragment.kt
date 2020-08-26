package com.example.github_api_application.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import com.example.github_api_application.BR
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment<VIEW_DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>(
    @LayoutRes layoutRes: Int, viewModelCls: BaseViewModel) : Fragment(layoutRes) {

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

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(viewLifecycleOwner) { data ->
            data?.let(action)
        }
    }

}