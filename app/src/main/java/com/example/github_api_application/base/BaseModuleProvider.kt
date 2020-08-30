package com.example.github_api_application.base

import org.koin.core.module.Module

interface BaseModuleProvider {
	val modules: List<Module>
}
