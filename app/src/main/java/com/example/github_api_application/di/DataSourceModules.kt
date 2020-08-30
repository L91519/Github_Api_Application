package com.example.github_api_application.di

import com.example.github_api_application.base.BaseModuleProvider
import com.example.github_api_application.base.BaseRemoteDataSource
import com.example.github_api_application.model.GithubRemoteDataSource
import com.example.github_api_application.model.GithubRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object DataSourceModules : BaseModuleProvider {

	override val modules: List<Module>
		get() = listOf(
			remoteDataSourceModule
		)

	private val remoteDataSourceModule = module {
		single { GithubRemoteDataSource(get(), get()) }
		single { GithubRepository(get()) }
	}
}
