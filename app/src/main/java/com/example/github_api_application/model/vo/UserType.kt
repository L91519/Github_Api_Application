package com.example.github_api_application.model.vo

enum class UserType(val value: String) {
    FOLLOWER("Follower"),
    FOLLOWING("Following"),
    STARGAZER("Stargazer");

    companion object {
        @Throws
        fun parse(value: String): UserType {
            return when (value) {
                "Follower" -> FOLLOWER
                "Following" -> FOLLOWING
                "Stargazer" -> STARGAZER
                else -> throw IllegalAccessException("not defined value")
            }
        }
    }
}