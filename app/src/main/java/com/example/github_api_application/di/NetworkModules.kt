package com.example.github_api_application.di

import com.example.github_api_application.api.GithubGsonConverterFactory
import com.example.github_api_application.api.GithubService
import com.example.github_api_application.base.BaseModuleProvider
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.TimeUnit

object NetworkModules : BaseModuleProvider {
    private const val TAG = "OkHttp"
    private const val connectTimeout = 7L
    private const val writeTimeout = 7L
    private const val readTimeout = 7L

    override val modules: List<Module>
        get() = listOf(retrofitModule, githubServiceModule)

    private val githubServiceModule = module {
        single { get<Retrofit>().create(GithubService.API::class.java) }
    }

    private val retrofitModule = module {
        single { provideGson() }
        single { provideOkHttpClient() }
        single { provideRetrofit(get(), GithubService.GITHUB_BASE_URL) }
    }

    private fun provideGson() = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .setPrettyPrinting()
        .create()

    private fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GithubGsonConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }
}