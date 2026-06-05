package id.my.sendiko.fintrack.auth.login.data

import id.my.sendiko.fintrack.auth.login.data.dto.LoginRequest
import id.my.sendiko.fintrack.auth.login.data.dto.LoginResponse
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl

class LoginRepository(
    private val ktorClient: KtorClient,
    private val prefs: PreferencesRepositoryImpl
) {

    suspend fun login(username: String, password: String): Result<LoginResponse, DataError.Remote> {
        val request = LoginRequest(name = username, password = password)
        return ktorClient.login(request)
    }

    suspend fun saveToken(token: String) {
        prefs.setToken(token)
    }

    suspend fun saveUserId(userId: String) {
        prefs.setUserId(userId)
    }

}