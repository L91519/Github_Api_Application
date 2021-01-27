package com.example.github_api_application.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    private val _serviceStart = MutableLiveData<Unit>()
    val serviceStart: LiveData<Unit> = _serviceStart

    private var startJob: Job? = null

    fun start() {
        startJob = viewModelScope.launch {
            delay(SPLASH_TIME_OUT)
            _serviceStart.value = Unit
        }
    }

    companion object {
        private const val SPLASH_TIME_OUT = 1500L
    }

}