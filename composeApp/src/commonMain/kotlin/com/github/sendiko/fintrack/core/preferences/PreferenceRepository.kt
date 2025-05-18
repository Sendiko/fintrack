package com.github.sendiko.fintrack.core.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    suspend fun setToken(token: String)

    fun getToken(): Flow<String>

}