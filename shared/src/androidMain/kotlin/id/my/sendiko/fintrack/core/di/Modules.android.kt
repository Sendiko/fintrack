package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.core.preferences.createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { createDataStore(androidContext()) }
        single<HttpClientEngine> { OkHttp.create() }
    }