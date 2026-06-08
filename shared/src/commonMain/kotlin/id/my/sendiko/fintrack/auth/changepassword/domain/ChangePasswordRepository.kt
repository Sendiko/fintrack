package id.my.sendiko.fintrack.auth.changepassword.domain

import id.my.sendiko.fintrack.auth.core.domain.User
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface ChangePasswordRepository {

    suspend fun searchUser(email: String): Result<User, DataError.Remote>

    suspend fun updatePassword(userId: String, password: String): Result<String, DataError.Remote>

}