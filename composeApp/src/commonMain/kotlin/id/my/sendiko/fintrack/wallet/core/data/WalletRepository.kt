package id.my.sendiko.fintrack.wallet.core.data

import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.wallet.core.data.dto.get.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletResponse
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.coroutines.flow.Flow

class WalletRepository(
    val ktorClient: KtorClient,
    val prefs: PreferencesRepositoryImpl
) {

    suspend fun getWallets(token: String, userId: String): Result<GetWalletsResponse, DataError.Remote> {
        return ktorClient.getWallets(token, userId)
    }

    suspend fun createWallet(token: String, userId: String, wallet: Wallet): Result<PostWalletResponse, DataError.Remote> {
        val request = PostWalletRequest(
            balance = wallet.amount,
            purpose = wallet.purpose,
            name = wallet.name,
            type = wallet.type,
            userId = userId,
        )
        return ktorClient.postWallet(token, request)
    }

    fun getUserId(): Flow<String> {
        return prefs.getUserId()
    }

    fun getToken(): Flow<String> {
        return prefs.getToken()
    }

}