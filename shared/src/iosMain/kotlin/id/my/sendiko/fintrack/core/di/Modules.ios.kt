package id.my.sendiko.fintrack.core.di

import id.my.sendiko.fintrack.core.preferences.createDataStore
import id.my.sendiko.fintrack.transaction.core.domain.OcrEngine
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { createDataStore() }
        single { OcrEngine() }
        single<HttpClientEngine> { Darwin.create() }
    }