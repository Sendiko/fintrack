package id.my.sendiko.fintrack

import android.app.Application
import id.my.sendiko.fintrack.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class FinTrackApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FinTrackApp)
        }
    }

}