package id.my.sendiko.fintrack.auth.changepassword.data

import id.my.sendiko.fintrack.auth.changepassword.data.dto.ChangePasswordRequest
import id.my.sendiko.fintrack.auth.changepassword.domain.ChangePasswordRepository
import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSource
import id.my.sendiko.fintrack.auth.core.domain.User
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class ChangePasswordRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : ChangePasswordRepository {

    override suspend fun searchUser(email: String): Result<User, DataError.Remote> {
        return when (val response = remoteDataSource.searchUser(email)) {
            is Result.Success -> Result.Success(response.data.userDto.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun updatePassword(
        userId: String,
        password: String
    ): Result<String, DataError.Remote> {
        val request = ChangePasswordRequest(password)
        return when (val response = remoteDataSource.updatePassword(userId, request)) {
            is Result.Success -> Result.Success(response.data.message)
            is Result.Error -> Result.Error(response.error)
        }
    }

}