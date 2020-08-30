package com.example.github_api_application

import android.app.Application
import com.example.github_api_application.di.DataSourceModules
import com.example.github_api_application.di.NetworkModules
import com.example.github_api_application.di.ViewModelModules
import com.example.github_api_application.utils.SharedPreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initPreference()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MyApplication)

            modules(ViewModelModules.modules)
            modules(NetworkModules.modules)
            modules(DataSourceModules.modules)
        }
    }

    private fun initPreference() {
        SharedPreferenceManager.create(this)
    }
}