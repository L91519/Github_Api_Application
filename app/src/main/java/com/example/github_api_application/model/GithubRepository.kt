package com.example.github_api_application.model

import com.example.github_api_application.base.resultFlow

class GithubRepository(private val githubRemoteDataSource: GithubRemoteDataSource) {

    suspend fun requestAccessToken(url: String, code: String) = resultFlow {
        githubRemoteDataSource.requestAccessToken(url, code)
    }

    suspend fun getUserInfoByAccessToken(accessToken: String) = resultFlow {
        githubRemoteDataSource.getUserInfoByAccessToken(accessToken)
    }

    suspend fun getUserInfo(userName: String) = resultFlow {
        githubRemoteDataSource.getUserInfo(userName)
    }

    suspend fun getFollowers(userName: String) = resultFlow {
        githubRemoteDataSource.getFollowers(userName)
    }

    suspend fun getFollowing(userName: String) = resultFlow {
        githubRemoteDataSource.getFollowing(userName)
    }

    suspend fun getWatchers(userName: String, repoName: String) = resultFlow {
        githubRemoteDataSource.getWatchers(userName, repoName)
    }

    suspend fun getStargazers(userName: String, repoName: String) = resultFlow {
        githubRemoteDataSource.getStargazers(userName, repoName)
    }

    suspend fun getRepository(userName: String, repoName: String) = resultFlow {
        githubRemoteDataSource.getRepository(userName, repoName)
    }

    suspend fun getUserRepos(userName: String) = resultFlow {
        githubRemoteDataSource.getUserRepos(userName)
    }

    suspend fun getStarredRepos(userName: String) = resultFlow {
        githubRemoteDataSource.getStarredRepos(userName)
    }

    suspend fun searchRepositories(keyword: String) = resultFlow {
        githubRemoteDataSource.searchRepositories(keyword)
    }

    suspend fun searchUsers(keyword: String) = resultFlow {
        githubRemoteDataSource.searchUsers(keyword)
    }

}