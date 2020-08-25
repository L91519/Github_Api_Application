package com.example.github_api_application.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VIEW_DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>(@LayoutRes layoutRes: Int) :
    Fragment(layoutRes) {

}