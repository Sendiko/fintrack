package com.github.sendiko.fintrack.core.di

import com.github.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import com.github.sendiko.fintrack.splash.data.SplashRepositoryImpl
import com.github.sendiko.fintrack.splash.presentation.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::SplashRepositoryImpl)

    factory { PreferencesRepositoryImpl(get()) }
    factory { SplashRepositoryImpl(get()) }
    factory { SplashViewModel(get()) }
}