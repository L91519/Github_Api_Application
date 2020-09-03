package com.example.github_api_application.ui.authorize

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.github_api_application.base.BaseViewModel
import com.example.github_api_application.model.GithubRepository
import androidx.navigation.findNavController
import com.example.github_api_application.api.GithubService
import com.example.github_api_application.utils.parseQueryString
import com.example.github_api_application.utils.toSingleEvent
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.URI

class AuthorizeViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {
    private val _navigateToUserDetail = MutableLiveData<String>()
    val navigateToUserDetail = _navigateToUserDetail

    fun requestAccessToken(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            code?.let {
                githubRepository.requestAccessToken("${GithubService.GITHUB_WEBVIEW_URL}${GithubService.requestToken}", it).collect {
                    val map = it?.parseQueryString()
                    val accessToken = map?.get("access_token")
                    _navigateToUserDetail.postValue(accessToken)
                }
            }
        }
    }
}