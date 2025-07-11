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
import id.my.sendiko.fintrack.wallet.core.data.GetWalletsResponse

interface ApiService {

    suspend fun register(request: RegisterRequest): Result<RegisterResponse, DataError.Remote>

    suspend fun login(request: LoginRequest): Result<LoginResponse, DataError.Remote>

    suspend fun searchUser(email: String): Result<SearchUserResponse, DataError.Remote>

    suspend fun updatePassword(userId: String, request: ChangePasswordRequest): Result<ChangePasswordResponse, DataError.Remote>

    suspend fun getWallets(token: String, userId: String): Result<GetWalletsResponse, DataError.Remote>

    suspend fun getCategories(token: String, userId: String): Result<GetCategoriesResponse, DataError.Remote>

    suspend fun getTransactions(token: String, userId: String): Result<GetTransactionsResponse, DataError.Remote>
}