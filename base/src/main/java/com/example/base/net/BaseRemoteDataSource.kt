package com.example.base.net

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRemoteDataSource(private val gson: Gson) {

    protected suspend fun <DATA> getResult(call: suspend () -> Response<DATA>): DATA? {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            return error(response.message())
        } catch (e: Exception) {
            val error = e.toString()
            return null
        }
    }
}

fun <T> resultFlow(call: suspend () -> T): Flow<T> {
    return flow {
        withContext(Dispatchers.IO) {
            emit(call.invoke())
        }
    }
}