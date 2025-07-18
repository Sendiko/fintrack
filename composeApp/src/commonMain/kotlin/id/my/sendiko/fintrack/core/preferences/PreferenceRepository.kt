package id.my.sendiko.fintrack.core.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    suspend fun setToken(token: String)

    fun getToken(): Flow<String>

    suspend fun setUserId(userId: String)

    fun getUserId(): Flow<String>

}