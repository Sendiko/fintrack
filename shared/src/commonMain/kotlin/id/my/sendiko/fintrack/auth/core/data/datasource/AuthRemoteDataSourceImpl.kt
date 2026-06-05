package id.my.sendiko.fintrack.auth.core.data.datasource

import id.my.sendiko.fintrack.auth.login.data.dto.LoginRequest
import id.my.sendiko.fintrack.auth.login.data.dto.LoginResponse
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterResponse
import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthRemoteDataSourceImpl(
    private val client: HttpClient
) : AuthRemoteDataSource {
    override suspend fun register(request: RegisterRequest): Result<RegisterResponse, DataError.Remote> {
        return safeCall<RegisterResponse> {
            client.post("register") {
                setBody(request)
            }
        }
    }

    override suspend fun login(request: LoginRequest): Result<LoginResponse, DataError.Remote> {
        return safeCall<LoginResponse> {
            client.post("login") {
                setBody(request)
            }
        }
    }
}