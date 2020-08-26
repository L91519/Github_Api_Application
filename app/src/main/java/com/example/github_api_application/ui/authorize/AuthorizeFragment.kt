package com.example.github_api_application.ui.authorize

import android.os.Bundle
import com.example.github_api_application.BuildConfig
import com.example.github_api_application.api.GithubService
import com.example.github_api_application.base.BaseWebViewFragment

class AuthorizeFragment : BaseWebViewFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.webView.loadUrl("${GithubService.webviewAuth}${BuildConfig.CLIENT_ID}")
    }
}