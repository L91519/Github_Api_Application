package com.example.github_api_application.ui.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_api_application.base.BaseViewModel
import com.example.github_api_application.model.GithubRepository
import com.example.github_api_application.model.vo.RepoType
import com.example.github_api_application.model.vo.Repository
import com.example.github_api_application.utils.cancelIfActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RepositoriesViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<Repository>>()
    val repoList = _repoList

    var repoListJob: Job? = null

    fun fetch(repoType: RepoType, userID: String) {
        when(repoType) {
            RepoType.STARRED_REPO -> getStarredRepoList(userID)
            RepoType.USER_REPO -> getUserRepoList(userID)
        }
    }

    private fun getStarredRepoList(userID: String) {
        repoListJob.cancelIfActive()
        repoListJob = viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getStarredRepos(userID).collect {
                _repoList.postValue(it)
            }
        }
    }

    private fun getUserRepoList(userID: String) {

    }

}