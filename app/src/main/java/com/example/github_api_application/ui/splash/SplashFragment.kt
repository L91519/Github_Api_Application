package com.example.github_api_application.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseActivity
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash, SplashViewModel::class.java)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()

        subscribeUI()
    }

    private fun setupUI() {

    }

    private fun subscribeUI() {
        viewModel.serviceStart.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthorizeFragment())
        })
    }

}