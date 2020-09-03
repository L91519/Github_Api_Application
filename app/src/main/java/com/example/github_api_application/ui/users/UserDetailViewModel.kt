package com.example.github_api_application.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_api_application.base.BaseViewModel
import com.example.github_api_application.model.GithubRepository
import com.example.github_api_application.model.vo.Repository
import com.example.github_api_application.model.vo.User
import com.example.github_api_application.utils.SharedPreferenceManager
import com.example.github_api_application.utils.cancelIfActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user = _user

    private val _userRepos = MutableLiveData<List<Repository>>()
    val userRepos = _userRepos

    private var authJob: Job? = null
    private var repositoryJob: Job? = null

    fun fetch() {
        getAuth()
    }

    private fun getAuth() {
        authJob?.cancelIfActive()
        authJob = viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getUserInfoByAccessToken(SharedPreferenceManager.getInstance().accessToken!!).collect {
                _user.postValue(it)
            }
        }
    }

    fun getUserRepos() {
        repositoryJob?.cancelIfActive()
        repositoryJob = viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getUserRepos(_user.value?.login!!).collect {
                _userRepos.postValue(it)
            }
        }
    }

}
