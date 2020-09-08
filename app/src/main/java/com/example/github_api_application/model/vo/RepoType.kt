package com.example.github_api_application.model.vo

enum class RepoType(val value: String) {
    STARRED_REPO("Starred Repository"),
    USER_REPO("User Repository");

    companion object {
        @Throws
        fun parse(value: String): RepoType {
            return when (value) {
                "Starred Repo" -> STARRED_REPO
                "User Repo" -> USER_REPO
                else -> throw IllegalAccessException("not defined value")
            }
        }
    }
}