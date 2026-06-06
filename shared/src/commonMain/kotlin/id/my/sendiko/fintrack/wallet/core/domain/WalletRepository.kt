package id.my.sendiko.fintrack.wallet.core.domain

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import kotlinx.coroutines.flow.Flow

interface WalletRepository {

    suspend fun getWallets(): Result<List<Wallet>, DataError.Remote>

    suspend fun createWallet(userId: String, wallet: Wallet): Result<Wallet, DataError.Remote>

    fun getUserId(): Flow<String>

    fun getToken(): Flow<String>

}