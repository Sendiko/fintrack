package id.my.sendiko.fintrack.auth.register.data

import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterResponse
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class RegisterRepository(
    private val ktorClient: KtorClient
) {

    suspend fun register(request: RegisterRequest): Result<RegisterResponse, DataError.Remote> {
        return ktorClient.register(request)
    }

}