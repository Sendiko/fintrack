package com.github.sendiko.fintrack.splash.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import com.github.sendiko.fintrack.splash.domain.SplashRepository
import kotlinx.coroutines.flow.Flow

class SplashRepositoryImpl(
    val preferences: PreferencesRepositoryImpl
) : SplashRepository {
    override fun getToken(): Flow<String> {
        return preferences.getToken()
    }

}