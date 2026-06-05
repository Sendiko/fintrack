package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.core.preferences.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val platformModule: Module
    get() = module {
        single { createDataStore() }
        single<HttpClientEngine> { Darwin.create() }
    }