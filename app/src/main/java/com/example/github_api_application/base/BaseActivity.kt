package com.example.github_api_application.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VIEW_DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>(@LayoutRes layoutRes: Int) :
    AppCompatActivity(layoutRes) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}