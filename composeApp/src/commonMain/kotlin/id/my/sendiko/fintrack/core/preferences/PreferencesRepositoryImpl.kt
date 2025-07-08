package id.my.sendiko.fintrack.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : PreferenceRepository {

    private val tokenKey = stringPreferencesKey("token")
    private val userIdKey = stringPreferencesKey("user_id")
    override suspend fun setToken(token: String) {
        dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    override fun getToken(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[tokenKey] ?: ""
        }
    }

    override suspend fun setUserId(userId: String) {
        dataStore.edit { prefs ->
            prefs[userIdKey] = userId
        }
    }

    override fun getUserId(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[userIdKey] ?: ""
        }
    }

}