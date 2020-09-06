package com.example.github_api_application.api

import com.example.github_api_application.BuildConfig
import com.example.github_api_application.model.vo.Repository
import com.example.github_api_application.model.vo.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

object GithubService {
    const val GITHUB_BASE_URL = "https://api.github.com"
    const val GITHUB_WEBVIEW_URL = "https://github.com"
    const val DEFAULT_PER_PAGE = 50

    const val requestToken = "/login/oauth/access_token"
    const val userByToken = "/user"
    const val userByName = "/users/{ownerName}"
    const val userFollowers = "/users/{ownerName}/followers"
    const val userFollowing = "/users/{ownerName}/following"
    const val watchers = "/repos/{ownerName}/{repoName}/watchers"
    const val stargazers = "/repos/{ownerName}/{repoName}/stargazers"
    const val repository = "/repos/{ownerName}/{repoName}"
    const val userRepositories = "/users/{ownerName}/repos"
    const val userStarredRepositories = "/users/{ownerName}/starred"
    const val searchRepositories = "/search/repositories"
    const val searchUsers = "/search/users"

    const val webViewAuth = "${GITHUB_WEBVIEW_URL}/login/oauth/authorize?client_id="

    interface API {

//        @GET(requestToken)
        @GET
        suspend fun requestAccessToken(
            @Url url: String,
            @Query("code") code: String,
            @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
            @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET
        ): Response<String>

        @GET(userByToken)
        suspend fun getUserInfoByAccessToken(@Query("access_token") accessToken: String): Response<User>

        @GET(userByName)
        suspend fun getUserInfo(@Path("ownerName") username: String): Response<User>

        @GET(userFollowers)
        suspend fun getFollowers(
            @Path("ownerName") username: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<User>>

        @GET(userFollowing)
        suspend fun getFollowing(
            @Path("ownerName") username: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<User>>

        @GET(watchers)
        suspend fun getWatchers(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<User>>

        @GET(stargazers)
        suspend fun getStargazers(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<User>>

        @GET(repository)
        suspend fun getRepository(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String
        ): Response<Repository>

        @GET(userRepositories)
        suspend fun getUserRepos(
            @Path("ownerName") ownerName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<Repository>>

        @GET(userStarredRepositories)
        suspend fun getStarredRepos(
            @Path("ownerName") ownerName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<Repository>>

        @GET(searchRepositories)
        suspend fun searchRepositories(
            @Query("q") keyword: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<Repository>>

        @GET(searchUsers)
        suspend fun searchUsers(
            @Query("q") keyword: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        ): Response<List<User>>
    }
}