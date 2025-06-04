package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.splash.data.SplashRepositoryImpl
import id.my.sendiko.fintrack.splash.presentation.SplashViewModel
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