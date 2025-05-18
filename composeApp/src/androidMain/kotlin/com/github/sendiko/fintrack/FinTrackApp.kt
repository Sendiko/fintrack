package com.github.sendiko.fintrack

import android.app.Application
import com.github.sendiko.fintrack.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class FinTrackApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FinTrackApp)
        }
    }

}