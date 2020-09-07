package com.example.github_api_application.ui.users

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_api_application.base.BaseViewModel
import com.example.github_api_application.model.GithubRepository
import com.example.github_api_application.model.vo.RepoType
import com.example.github_api_application.model.vo.Repository
import com.example.github_api_application.model.vo.User
import com.example.github_api_application.model.vo.UserType
import com.example.github_api_application.utils.SharedPreferenceManager
import com.example.github_api_application.utils.cancelIfActive
import com.example.github_api_application.utils.toSingleEvent
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user = _user

    private val _userRepos = MutableLiveData<List<Repository>>()
    val userRepos = _userRepos

    private val _navigateToUserList = LiveEvent<UserType>()
    val navigateToUserList = _navigateToUserList.toSingleEvent()

    private val _navigateToRepoList = LiveEvent<RepoType>()
    val navigateToRepoList = _navigateToRepoList.toSingleEvent()

    private var authJob: Job? = null
    private var repositoryJob: Job? = null

    fun fetch(id: String? = null) {
        getAuth(id)
    }

    private fun getAuth(id: String? = null) {
        authJob?.cancelIfActive()
        id?.let {
            authJob = viewModelScope.launch(Dispatchers.IO) {
                githubRepository.getUserInfo(id).collect {
                    _user.postValue(it)
                }
            }
        } ?: run {
        authJob = viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getUserInfoByAccessToken(SharedPreferenceManager.getInstance().accessToken!!).collect {
                _user.postValue(it)
            }
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

fun navigateToUserList(view: View) {
    _navigateToUserList.value = UserType.parse((view as TextView).text.toString())
}

fun navigateToRepoList(view: View) {
    _navigateToRepoList.value = RepoType.parse((view as TextView).text.toString())
}

}
