package id.my.sendiko.fintrack.auth.login.domain

import id.my.sendiko.fintrack.auth.login.domain.model.UserWithToken
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface LoginRepository {

    suspend fun login(username: String, password: String): Result<UserWithToken, DataError.Remote>

    suspend fun saveToken(token: String)

    suspend fun saveUserId(userId: String)

}