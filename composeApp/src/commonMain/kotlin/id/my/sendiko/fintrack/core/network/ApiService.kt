package id.my.sendiko.fintrack.core.network

import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface ApiService {

    suspend fun register(request: RegisterRequest): Result<RegisterResponse, DataError.Remote>

}