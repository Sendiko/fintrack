package id.my.sendiko.fintrack.core.network

import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

const val BASE_URL = "https://fintrack.sendiko.my.id"

class KtorClient(
    private val client: HttpClient
): ApiService {
    override suspend fun register(request: RegisterRequest): Result<RegisterResponse, DataError.Remote> {
        return safeCall<RegisterResponse> {
            client.post("$BASE_URL/register") {
                setBody(request)
            }
        }
    }

}