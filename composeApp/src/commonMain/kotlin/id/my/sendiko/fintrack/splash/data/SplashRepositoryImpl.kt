package id.my.sendiko.fintrack.splash.data

import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.splash.domain.SplashRepository
import kotlinx.coroutines.flow.Flow

class SplashRepositoryImpl(
    val preferences: PreferencesRepositoryImpl
) : SplashRepository {
    override fun getToken(): Flow<String> {
        return preferences.getToken()
    }

}