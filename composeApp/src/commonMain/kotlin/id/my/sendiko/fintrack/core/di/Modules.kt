package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.auth.changepassword.data.ChangePasswordRepository
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordViewModel
import id.my.sendiko.fintrack.auth.login.data.LoginRepository
import id.my.sendiko.fintrack.auth.login.presentation.LoginViewModel
import id.my.sendiko.fintrack.auth.register.data.RegisterRepository
import id.my.sendiko.fintrack.auth.register.presentation.RegisterViewModel
import id.my.sendiko.fintrack.core.network.ApiService
import id.my.sendiko.fintrack.core.network.HttpClientFactory
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.dashboard.data.DashboardRepository
import id.my.sendiko.fintrack.dashboard.presentation.DashboardViewModel
import id.my.sendiko.fintrack.splash.data.SplashRepositoryImpl
import id.my.sendiko.fintrack.splash.presentation.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorClient).bind<ApiService>()
    singleOf(::SplashRepositoryImpl)
    singleOf(::RegisterRepository)
    singleOf(::LoginRepository)
    singleOf(::ChangePasswordRepository)
    singleOf(::ChangePasswordRepository)
    singleOf(::DashboardRepository)

    factory { PreferencesRepositoryImpl(get()) }
    factory { SplashRepositoryImpl(get()) }
    factory { SplashViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { ChangePasswordViewModel(get()) }
    factory { DashboardViewModel(get()) }
}