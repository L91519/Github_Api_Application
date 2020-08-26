package com.example.github_api_application.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.github_api_application.BuildConfig

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

    const val webviewAuth = "$GITHUB_WEBVIEW_URL/login/oauth/authorize?client_id="

    interface API {
        @POST(requestToken)
        fun requestAccessToken(
            @Query("code") code: String,
            @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
            @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET
        )

        @GET(userByToken)
        fun getUserInfoByAccessToken(@Query("access_token") accessToken: String)

        @GET(userByName)
        fun getUserInfo(@Path("ownerName") username: String)

        @GET(userFollowers)
        fun getFollowers(
            @Path("ownerName") username: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(userFollowing)
        fun getFollowing(
            @Path("ownerName") username: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(watchers)
        fun getWatchers(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(stargazers)
        fun getStargazers(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(repository)
        fun getRepository(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String
        )

        @GET(userRepositories)
        fun getUserRepos(
            @Path("ownerName") ownerName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(userStarredRepositories)
        fun getStarredRepos(
            @Path("ownerName") ownerName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(searchRepositories)
        fun searchRepositories(
            @Query("q") keyword: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET(searchUsers)
        fun searchUsers(
            @Query("q") keyword: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )
    }
}