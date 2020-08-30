package com.example.github_api_application.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_api_application.base.BaseViewModel
import com.example.github_api_application.model.GithubRepository
import com.example.github_api_application.model.vo.User
import com.example.github_api_application.utils.SharedPreferenceManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user = _user

    fun fetch() {
        viewModelScope.launch {
            githubRepository.getUserInfoByAccessToken(SharedPreferenceManager.getInstance().accessToken!!).collect {
                _user.postValue(it)
            }
        }
    }

}