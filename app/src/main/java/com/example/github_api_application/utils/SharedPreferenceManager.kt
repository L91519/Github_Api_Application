package com.example.github_api_application.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager private constructor(private val sharedPreferences: SharedPreferences) {

    var accessToken: String? by sharedPreferences.string(null)

    companion object {
        private const val PREF_INFO = "preferenceInfo"

        private var instance: SharedPreferenceManager? = null

        fun getInstance(): SharedPreferenceManager {
            synchronized(this) {
                return instance ?: error("instance has not created. if you want use Preference, create first.")
            }
        }

        fun create(context: Context) {
            val preferences = context.getSharedPreferences(PREF_INFO, Context.MODE_PRIVATE)
            instance = SharedPreferenceManager(preferences)
        }
    }

}