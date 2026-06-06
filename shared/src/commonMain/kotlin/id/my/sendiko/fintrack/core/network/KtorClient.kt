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
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

const val BASE_URL = "https://fintrack.sendiko.my.id/"

class KtorClient(
    private val client: HttpClient
) : ApiService {
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

    override suspend fun searchUser(email: String): Result<SearchUserResponse, DataError.Remote> {
        return safeCall<SearchUserResponse> {
            client.get("users") {
                parameter("email", email)
            }
        }
    }

    override suspend fun updatePassword(
        userId: String,
        request: ChangePasswordRequest
    ): Result<ChangePasswordResponse, DataError.Remote> {
        return safeCall<ChangePasswordResponse> {
            client.put("user/change-password/$userId") {
                setBody(request)
            }
        }
    }

    override suspend fun getWallets(): Result<GetWalletsResponse, DataError.Remote> {
        return safeCall<GetWalletsResponse> {
            client.get("wallets")
        }
    }

    override suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote> {
        return safeCall<GetCategoriesResponse> {
            client.get("categories")
        }
    }

    override suspend fun getTransactions(): Result<GetTransactionsResponse, DataError.Remote> {
        return safeCall<GetTransactionsResponse> {
            client.get("transactions")
        }
    }

    override suspend fun postWallet(request: PostWalletRequest): Result<PostWalletResponse, DataError.Remote> {
        return safeCall<PostWalletResponse> {
            client.post("wallets") {
                setBody(request)
            }
        }
    }

}