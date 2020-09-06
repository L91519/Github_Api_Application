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

    private val _navigateToBack = LiveEvent<Unit>()
    val navigateToBack = _navigateToBack.toSingleEvent()

    private var userListJob: Job? = null

    fun fetch(userType: UserType) {
        when (userType) {
            UserType.FOLLOWER -> getFollowerList()
            UserType.FOLLOWING -> getFollowingList()
            UserType.STARGAZER -> getStargazerList()
        }
    }

    private fun getUserID(): String = SharedPreferenceManager.getInstance().userID!!

    private fun getFollowerList() {
        userListJob?.cancelIfActive()
        userListJob = viewModelScope.launch (Dispatchers.IO){
            githubRepository.getFollowers(getUserID()).collect {
                _userList.postValue(it)
            }
        }
    }

    private fun getFollowingList() {
        userListJob?.cancelIfActive()
        userListJob = viewModelScope.launch (Dispatchers.IO){
            githubRepository.getFollowing(getUserID()).collect {
                _userList.postValue(it)
            }
        }
    }

    private fun getStargazerList() {
        userListJob?.cancelIfActive()
        userListJob = viewModelScope.launch (Dispatchers.IO){

        }
    }

    fun onClickBack() {
        _navigateToBack.value = Unit
    }

}