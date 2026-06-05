package id.my.sendiko.fintrack.auth.login.data

import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSource
import id.my.sendiko.fintrack.auth.login.data.dto.LoginRequest
import id.my.sendiko.fintrack.auth.login.domain.LoginRepository
import id.my.sendiko.fintrack.auth.login.domain.model.UserWithToken
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl

class LoginRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val prefs: PreferencesRepositoryImpl
) : LoginRepository {

    override suspend fun login(
        username: String,
        password: String
    ): Result<UserWithToken, DataError.Remote> {
        val request = LoginRequest(name = username, password = password)
        return when (val response = remoteDataSource.login(request)) {
            is Result.Success -> Result.Success(response.data.userItem.toDomainWithToken())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun saveToken(token: String) {
        prefs.setToken(token)
    }

    override suspend fun saveUserId(userId: String) {
        prefs.setUserId(userId)
    }

}