package com.example.github_api_application.base

import com.example.github_api_application.utils.parseQueryString
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRemoteDataSource(private val gson: Gson) {

    protected suspend fun <DATA> getResult(call: suspend () -> Response<DATA>): DATA {
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
            return error(error)
        }
    }

    protected suspend fun <DATA> requestCodeResult(call: suspend () -> Response<DATA>): String {
        val response = call().raw().toString()

        return response
    }

}

fun <T> resultFlow(call: suspend () -> T): Flow<T> {
    return flow {
        withContext(Dispatchers.IO) {
            emit(call.invoke())
        }
    }
}