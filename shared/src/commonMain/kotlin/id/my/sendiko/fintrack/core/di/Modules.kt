package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.auth.changepassword.data.ChangePasswordRepositoryImpl
import id.my.sendiko.fintrack.auth.changepassword.domain.ChangePasswordRepository
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordViewModel
import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSource
import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSourceImpl
import id.my.sendiko.fintrack.auth.login.data.LoginRepositoryImpl
import id.my.sendiko.fintrack.auth.login.domain.LoginRepository
import id.my.sendiko.fintrack.auth.login.presentation.LoginViewModel
import id.my.sendiko.fintrack.auth.register.data.RegisterRepositoryImpl
import id.my.sendiko.fintrack.auth.register.domain.RegisterRepository
import id.my.sendiko.fintrack.auth.register.presentation.RegisterViewModel
import id.my.sendiko.fintrack.category.core.data.CategoryRepositoryImpl
import id.my.sendiko.fintrack.category.core.data.datasource.CategoryDataSource
import id.my.sendiko.fintrack.category.core.data.datasource.CategoryDataSourceImpl
import id.my.sendiko.fintrack.category.core.domain.CategoryRepository
import id.my.sendiko.fintrack.category.list.presentation.ListCategoryViewModel
import id.my.sendiko.fintrack.core.network.HttpClientFactory
import id.my.sendiko.fintrack.core.preferences.PreferenceRepository
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.dashboard.data.DashboardRepositoryImpl
import id.my.sendiko.fintrack.dashboard.domain.DashboardRepository
import id.my.sendiko.fintrack.dashboard.presentation.DashboardViewModel
import id.my.sendiko.fintrack.splash.data.SplashRepositoryImpl
import id.my.sendiko.fintrack.splash.domain.SplashRepository
import id.my.sendiko.fintrack.splash.presentation.SplashViewModel
import id.my.sendiko.fintrack.transaction.core.data.TransactionRepositoryImpl
import id.my.sendiko.fintrack.transaction.core.data.datasource.TransactionDataSource
import id.my.sendiko.fintrack.transaction.core.data.datasource.TransactionDataSourceImpl
import id.my.sendiko.fintrack.transaction.core.domain.TransactionRepository
import id.my.sendiko.fintrack.transaction.form.presentation.FormTransactionViewModel
import id.my.sendiko.fintrack.transaction.list.presentation.ListTransactionViewModel
import id.my.sendiko.fintrack.wallet.core.data.WalletRepositoryImpl
import id.my.sendiko.fintrack.wallet.core.data.datasource.WalletDataSource
import id.my.sendiko.fintrack.wallet.core.data.datasource.WalletDataSourceImpl
import id.my.sendiko.fintrack.wallet.core.domain.WalletRepository
import id.my.sendiko.fintrack.wallet.form.presentation.FormWalletViewModel
import id.my.sendiko.fintrack.wallet.list.presentation.ListWalletViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get(), get()) }
    singleOf(::PreferencesRepositoryImpl).bind<PreferenceRepository>()
    singleOf(::SplashRepositoryImpl).bind<SplashRepository>()
    singleOf(::AuthRemoteDataSourceImpl).bind<AuthRemoteDataSource>()
    singleOf(::RegisterRepositoryImpl).bind<RegisterRepository>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::ChangePasswordRepositoryImpl).bind<ChangePasswordRepository>()
    singleOf(::DashboardRepositoryImpl).bind<DashboardRepository>()
    singleOf(::WalletDataSourceImpl).bind<WalletDataSource>()
    singleOf(::WalletRepositoryImpl).bind<WalletRepository>()
    singleOf(::CategoryDataSourceImpl).bind<CategoryDataSource>()
    singleOf(::CategoryRepositoryImpl).bind<CategoryRepository>()
    singleOf(::TransactionDataSourceImpl).bind<TransactionDataSource>()
    singleOf(::TransactionRepositoryImpl).bind<TransactionRepository>()

    factory { SplashViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { ChangePasswordViewModel(get()) }
    factory { DashboardViewModel(get()) }
    factory { ListCategoryViewModel(get()) }
    factory { FormWalletViewModel(get()) }
    factory { ListWalletViewModel(get()) }
    factory { FormTransactionViewModel(get(), get(), get()) }
    factory { ListTransactionViewModel(get()) }
}