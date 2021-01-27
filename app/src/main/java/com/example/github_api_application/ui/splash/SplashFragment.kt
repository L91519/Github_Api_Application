package com.example.github_api_application.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.base.ui.BaseFragment
import com.example.github_api_application.R
import com.example.github_api_application.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash, SplashViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()

        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel {
            serviceStart.onResult {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthorizeFragment())
            }
        }
    }

}