package com.example.github_api_application.api

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Scope(val baseUrl: String)