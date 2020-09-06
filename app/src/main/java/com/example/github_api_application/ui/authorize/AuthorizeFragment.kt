package com.example.github_api_application.ui.authorize

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.*
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.github_api_application.BuildConfig
import com.example.github_api_application.api.GithubService
import com.example.github_api_application.base.BaseWebViewFragment
import com.example.github_api_application.model.GithubRepository
import com.example.github_api_application.utils.SharedPreferenceManager
import com.example.github_api_application.utils.parseQueryString
import com.example.github_api_application.utils.string
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URI

class AuthorizeFragment : BaseWebViewFragment() {

    private val viewModel by viewModel(clazz = AuthorizeViewModel::class.java.kotlin)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        binding.webView.loadUrl("${GithubService.webViewAuth}${BuildConfig.CLIENT_ID}")

        viewModel.navigateToUserDetail.observe(viewLifecycleOwner, Observer {
            saveAccessToken(it)
            findNavController().navigate(AuthorizeFragmentDirections.actionAuthorizeFragmentToUserDetailFragment())
        })
        initWebView()
    }

    private fun initWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    if (url.toString().contains("code=")) {
                        val map = URI(url.toString()).query.parseQueryString()
                        val code = map["code"]

                        code?.let { viewModel.requestAccessToken(it) }

//                        showLoading("Logging in...")
                    }
                }
            }
            webChromeClient = DefaultChromeWebViewClient()
        }
        val url = binding.webView.url

    }

    private fun saveAccessToken(accessToken: String) {
        SharedPreferenceManager.getInstance().accessToken = accessToken
    }

    fun receivedTitle(title: String?) {
        binding.toolbar.title = title
    }

    open class DefaultWebViewClient() : WebViewClient() {
    }

    class DefaultChromeWebViewClient() : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            if (URLUtil.isValidUrl(title)) return
//            if (title != null) receivedTitle(title)
        }

        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
//            val priority = when(consoleMessage?.messageLevel()) {
//                ConsoleMessage.MessageLevel.TIP -> Logger.INFO
//                ConsoleMessage.MessageLevel.LOG -> Logger.VERBOSE
//                ConsoleMessage.MessageLevel.WARNING -> Logger.WARN
//                ConsoleMessage.MessageLevel.ERROR -> Logger.ERROR
//                ConsoleMessage.MessageLevel.DEBUG -> Logger.DEBUG
//                else -> Logger.VERBOSE
//            }
            return true
        }
    }

    companion object {
        private const val PREF_USER_INFO = "userInfo"

    }

}