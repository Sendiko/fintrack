package id.my.sendiko.fintrack.dashboard.data

import id.my.sendiko.fintrack.category.data.GetCategoriesResponse
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.transaction.data.GetTransactionsResponse
import id.my.sendiko.fintrack.wallet.data.GetWalletsResponse
import kotlinx.coroutines.flow.Flow

class DashboardRepository(
    private val ktorClient: KtorClient,
    private val prefs: PreferencesRepositoryImpl
){

    fun getToken(): Flow<String> {
        return prefs.getToken()
    }

    suspend fun getWallets(token: String, userId: String): Result<GetWalletsResponse, DataError.Remote> {
        return ktorClient.getWallets(token, userId)
    }

    suspend fun getCategories(token: String, userId: String): Result<GetCategoriesResponse, DataError.Remote> {
        return ktorClient.getCategories(token, userId)
    }

    suspend fun getTransactions(token: String, userId: String): Result<GetTransactionsResponse, DataError.Remote> {
        return ktorClient.getTransactions(token, userId)
    }

    fun getUserId(): Flow<String> {
        return prefs.getUserId()
    }

}