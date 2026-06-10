package id.my.sendiko.fintrack.wallet.core.data

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferenceRepository
import id.my.sendiko.fintrack.wallet.core.data.datasource.WalletDataSource
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.put.PutWalletRequest
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import id.my.sendiko.fintrack.wallet.core.domain.WalletRepository
import kotlinx.coroutines.flow.Flow

class WalletRepositoryImpl(
    val remoteDataSource: WalletDataSource,
    val prefs: PreferenceRepository
) : WalletRepository {

    override suspend fun getWallets(): Result<List<Wallet>, DataError.Remote> {
        return when (val response = remoteDataSource.getWallets()) {
            is Result.Success -> Result.Success(response.data.wallets.map { it.toDomain() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun getWallet(id: String): Result<Wallet, DataError.Remote> {
        return when (val response = remoteDataSource.getWallet(id)) {
            is Result.Success -> Result.Success(response.data.wallet.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun createWallet(
        userId: String,
        wallet: Wallet
    ): Result<Wallet, DataError.Remote> {
        val request = PostWalletRequest(
            balance = wallet.amount,
            purpose = wallet.purpose,
            name = wallet.name,
            type = wallet.type,
            userId = userId,
        )
        return when (val response = remoteDataSource.postWallet(request)) {
            is Result.Success -> Result.Success(response.data.wallet.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun updateWallet(
        userId: String,
        wallet: Wallet
    ): Result<Wallet, DataError.Remote> {
        val request = PutWalletRequest(
            balance = wallet.amount,
            purpose = wallet.purpose,
            name = wallet.name,
            type = wallet.type,
            userId = userId
        )
        return when (val response = remoteDataSource.putWallet(wallet.id, request)) {
            is Result.Success -> Result.Success(response.data.wallet.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun deleteWallet(id: String): Result<String, DataError.Remote> {
        return when (val response = remoteDataSource.deleteWallet(id)) {
            is Result.Success -> Result.Success(response.data.message)
            is Result.Error -> Result.Error(response.error)
        }
    }

    override fun getUserId(): Flow<String> {
        return prefs.getUserId()
    }

    override fun getToken(): Flow<String> {
        return prefs.getToken()
    }

}