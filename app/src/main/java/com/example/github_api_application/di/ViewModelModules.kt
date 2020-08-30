package com.example.github_api_application.di

import com.example.github_api_application.base.BaseModuleProvider
import com.example.github_api_application.ui.authorize.AuthorizeViewModel
import com.example.github_api_application.ui.splash.SplashViewModel
import com.example.github_api_application.ui.users.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModules: BaseModuleProvider {

    override val modules: List<Module>
        get() = listOf(viewModelModule)

    private val viewModelModule = module {
        viewModel { SplashViewModel() }
        viewModel { AuthorizeViewModel(get()) }
        viewModel { UserDetailViewModel() }
    }
}