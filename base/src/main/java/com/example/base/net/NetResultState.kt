package com.example.base

sealed class NetResultState<out T> {
    data class Success<T>(val result: T): NetResultState<T>()
    data class Error<T>(val result: T): NetResultState<T>()
    object Loading: NetResultState<Nothing>()
}

data class NetResponding<out T> (
    val data: T
)
