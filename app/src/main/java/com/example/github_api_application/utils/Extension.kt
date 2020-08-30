package com.example.github_api_application.utils

import androidx.lifecycle.LiveData
import com.hadilq.liveevent.LiveEvent

fun String.parseQueryString(): Map<String,String> {
    val map = HashMap<String,String>()

    split("&").forEach {
        val list = it.split("=")
        map[list[0]] = list[1]
    }
    return map
}

fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val result = LiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}