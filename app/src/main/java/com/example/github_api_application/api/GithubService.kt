package com.example.github_api_application.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

object GithubService {
    const val GITHUB_BASE_URL = "https://api.github.com/"

    interface API {
        @POST("login/oauth/access_token")
        fun requestAccessToken(
            @Query("code") code: String,
            @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
            @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET
        )

        @GET("user")
        fun getUserInfoByAccessToken(@Query("access_token") accessToken: String)

        @GET("users/{ownerName}")
        fun getUserInfo(@Path("ownerName") username: String)

        @GET("users/{ownerName}/followers")
        fun getFollowers(
            @Path("ownerName") username: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("users/{ownerName}/following")
        fun getFollowing(
            @Path("ownerName") username: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("repos/{ownerName}/{repoName}/watchers")
        fun getWatchers(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("repos/{ownerName}/{repoName}/stargazers")
        fun getStargazers(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("repos/{ownerName}/{repoName}")
        fun getRepository(
            @Path("ownerName") ownerName: String,
            @Path("repoName") repoName: String
        )

        @GET("users/{ownerName}/repos")
        fun getUserRepos(
            @Path("ownerName") ownerName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("users/{ownerName}/starred")
        fun getStarredRepos(
            @Path("ownerName") ownerName: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("search/repositories")
        fun searchRepositories(
            @Query("q") keyword: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )

        @GET("search/users")
        fun searchUsers(
            @Query("q") keyword: String,
            @Query("page") page: Int = 1,
            @Query("per_page") per_page: Int = DEFAULT_PER_PAGE
        )
    }
}