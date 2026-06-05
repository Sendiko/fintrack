package id.my.sendiko.fintrack.auth.register.data

import id.my.sendiko.fintrack.auth.core.data.datasource.AuthRemoteDataSource
import id.my.sendiko.fintrack.auth.core.domain.User
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.domain.RegisterRepository
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class RegisterRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : RegisterRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User, DataError.Remote> {
        val request = RegisterRequest(
            name = name,
            email = email,
            password = password
        )
        return when (val response = remoteDataSource.register(request)) {
            is Result.Success -> Result.Success(response.data.userItem.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

}