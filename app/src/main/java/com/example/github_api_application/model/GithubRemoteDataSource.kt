package com.example.github_api_application.model

import com.example.github_api_application.api.GithubService
import com.example.github_api_application.base.BaseRemoteDataSource
import com.google.gson.Gson

class GithubRemoteDataSource(gson: Gson, private val githubAPI: GithubService.API) :
    BaseRemoteDataSource(gson) {

    suspend fun requestAccessToken(url: String, code: String) = getResult {
        githubAPI.requestAccessToken(url, code)
    }

    suspend fun getUserInfoByAccessToken(accessToken: String) = getResult {
        githubAPI.getUserInfoByAccessToken(accessToken)
    }

    suspend fun getUserInfo(userName: String) = getResult {
        githubAPI.getUserInfo(userName)
    }

    suspend fun getFollowers(userName: String) = getResult {
        githubAPI.getFollowers(userName)
    }

    suspend fun getFollowing(userName: String) = getResult {
        githubAPI.getFollowing(userName)
    }

    suspend fun getWatchers(userName: String, repoName: String) = getResult {
        githubAPI.getWatchers(userName, repoName)
    }

    suspend fun getStargazers(userName: String, repoName: String) = getResult {
        githubAPI.getStargazers(userName, repoName)
    }

    suspend fun getRepository(userName: String, repoName: String) = getResult {
        githubAPI.getRepository(userName, repoName)
    }

    suspend fun getUserRepos(userName: String) = getResult {
        githubAPI.getUserRepos(userName)
    }

    suspend fun getStarredRepos(userName: String) = getResult {
        githubAPI.getStarredRepos(userName)
    }

    suspend fun searchRepositories(keyword: String) = getResult {
        githubAPI.searchRepositories(keyword)
    }

    suspend fun searchUsers(keyword: String) = getResult {
        githubAPI.searchUsers(keyword)
    }

}