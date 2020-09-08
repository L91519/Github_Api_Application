package com.example.github_api_application.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_api_application.base.BaseViewModel
import com.example.github_api_application.model.GithubRepository
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

class UsersViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList = _userList

    private val _navigateToUserDetail = LiveEvent<User>()
    val navigateToUserDetail = _navigateToUserDetail.toSingleEvent()

    private val _navigateToBack = LiveEvent<Unit>()
    val navigateToBack = _navigateToBack.toSingleEvent()

    private var userListJob: Job? = null

    fun fetch(userType: UserType, userID: String) {
        when (userType) {
            UserType.FOLLOWER -> getFollowerList(userID)
            UserType.FOLLOWING -> getFollowingList(userID)
        }
    }

    private fun getFollowerList(userID: String) {
        userListJob?.cancelIfActive()
        userListJob = viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getFollowers(userID).collect {
                _userList.postValue(it)
            }
        }
    }

    private fun getFollowingList(userID: String) {
        userListJob?.cancelIfActive()
        userListJob = viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getFollowing(userID).collect {
                _userList.postValue(it)
            }
        }
    }

    fun onClickBack() {
        _navigateToBack.value = Unit
    }

    fun onClickUser(item: User) {
        _navigateToUserDetail.value = item
    }

}