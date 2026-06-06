package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.auth.changepassword.data.ChangePasswordRepository
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordViewModel
import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSource
import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSourceImpl
import id.my.sendiko.fintrack.auth.login.data.LoginRepositoryImpl
import id.my.sendiko.fintrack.auth.login.domain.LoginRepository
import id.my.sendiko.fintrack.auth.login.presentation.LoginViewModel
import id.my.sendiko.fintrack.auth.register.data.RegisterRepositoryImpl
import id.my.sendiko.fintrack.auth.register.domain.RegisterRepository
import id.my.sendiko.fintrack.auth.register.presentation.RegisterViewModel
import id.my.sendiko.fintrack.core.network.ApiService
import id.my.sendiko.fintrack.core.network.HttpClientFactory
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.dashboard.data.DashboardRepository
import id.my.sendiko.fintrack.dashboard.presentation.DashboardViewModel
import id.my.sendiko.fintrack.splash.data.SplashRepositoryImpl
import id.my.sendiko.fintrack.splash.presentation.SplashViewModel
import id.my.sendiko.fintrack.wallet.core.data.WalletRepositoryImpl
import id.my.sendiko.fintrack.wallet.core.data.datasource.WalletDataSource
import id.my.sendiko.fintrack.wallet.core.data.datasource.WalletDataSourceImpl
import id.my.sendiko.fintrack.wallet.core.domain.WalletRepository
import id.my.sendiko.fintrack.wallet.create.presentation.CreateWalletViewModel
import id.my.sendiko.fintrack.wallet.list.presentation.WalletListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get(), get()) }
    singleOf(::KtorClient).bind<ApiService>()
    singleOf(::SplashRepositoryImpl)
    singleOf(::AuthRemoteDataSourceImpl).bind<AuthRemoteDataSource>()
    singleOf(::RegisterRepositoryImpl).bind<RegisterRepository>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::ChangePasswordRepository)
    singleOf(::ChangePasswordRepository)
    singleOf(::DashboardRepository)
    singleOf(::WalletDataSourceImpl).bind<WalletDataSource>()
    singleOf(::WalletRepositoryImpl).bind<WalletRepository>()

    factory { PreferencesRepositoryImpl(get()) }
    factory { SplashRepositoryImpl(get()) }
    factory { SplashViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { ChangePasswordViewModel(get()) }
    factory { DashboardViewModel(get()) }
    factory { CreateWalletViewModel(get()) }
    factory { WalletListViewModel(get()) }
}