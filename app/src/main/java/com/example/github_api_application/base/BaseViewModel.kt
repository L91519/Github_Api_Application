package com.example.github_api_application.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github_api_application.utils.toSingleEvent

abstract class BaseViewModel : ViewModel() {
    private val _showToast = MutableLiveData<String>()
    val showToast = _showToast.toSingleEvent()
}