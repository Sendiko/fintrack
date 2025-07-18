package id.my.sendiko.fintrack.core.network

import id.my.sendiko.fintrack.auth.changepassword.data.dto.ChangePasswordRequest
import id.my.sendiko.fintrack.auth.changepassword.data.dto.ChangePasswordResponse
import id.my.sendiko.fintrack.auth.changepassword.data.dto.SearchUserResponse
import id.my.sendiko.fintrack.auth.login.data.dto.LoginRequest
import id.my.sendiko.fintrack.auth.login.data.dto.LoginResponse
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterResponse
import id.my.sendiko.fintrack.category.data.GetCategoriesResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.data.GetTransactionsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.get.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.create.data.PostWalletRequest
import id.my.sendiko.fintrack.wallet.create.data.PostWalletResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
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

    override suspend fun login(request: LoginRequest): Result<LoginResponse, DataError.Remote> {
        return safeCall<LoginResponse> {
            client.post("$BASE_URL/login") {
                setBody(request)
            }
        }
    }

    override suspend fun searchUser(email: String): Result<SearchUserResponse, DataError.Remote> {
        return safeCall<SearchUserResponse> {
            client.get("$BASE_URL/users") {
                parameter("email", email)
            }
        }
    }

    override suspend fun updatePassword(userId: String, request: ChangePasswordRequest): Result<ChangePasswordResponse, DataError.Remote> {
        return safeCall<ChangePasswordResponse> {
            client.put("$BASE_URL/user/change-password/$userId") {
                setBody(request)
            }
        }
    }

    override suspend fun getWallets(token: String, userId: String): Result<GetWalletsResponse, DataError.Remote> {
        return safeCall<GetWalletsResponse> {
            client.get("$BASE_URL/wallets/$userId") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun getCategories(token: String, userId: String): Result<GetCategoriesResponse, DataError.Remote> {
        return safeCall<GetCategoriesResponse> {
            client.get("$BASE_URL/categories/$userId") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun getTransactions(token: String, userId: String): Result<GetTransactionsResponse, DataError.Remote> {
        return safeCall<GetTransactionsResponse> {
            client.get("$BASE_URL/transactions/$userId") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun postWallet(
        token: String,
        request: PostWalletRequest
    ): Result<PostWalletResponse, DataError.Remote> {
        return safeCall<PostWalletResponse> {
            client.post("$BASE_URL/wallets") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

}