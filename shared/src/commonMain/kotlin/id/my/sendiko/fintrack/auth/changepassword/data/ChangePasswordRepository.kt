package id.my.sendiko.fintrack.auth.changepassword.data

import id.my.sendiko.fintrack.auth.changepassword.data.dto.ChangePasswordRequest
import id.my.sendiko.fintrack.auth.changepassword.data.dto.ChangePasswordResponse
import id.my.sendiko.fintrack.auth.changepassword.data.dto.SearchUserResponse
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class ChangePasswordRepository(
    private val ktorClient: KtorClient
) {

    suspend fun searchUser(email: String): Result<SearchUserResponse, DataError.Remote> {
        return ktorClient.searchUser(email)
    }

    suspend fun updatePassword(userId: String, password: String): Result<ChangePasswordResponse, DataError.Remote> {
        val request = ChangePasswordRequest(password)
        return ktorClient.updatePassword(userId, request)
    }

}