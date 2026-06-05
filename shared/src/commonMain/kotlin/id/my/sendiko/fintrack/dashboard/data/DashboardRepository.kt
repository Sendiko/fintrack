package id.my.sendiko.fintrack.dashboard.data

import id.my.sendiko.fintrack.category.data.GetCategoriesResponse
import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.transaction.data.GetTransactionsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletsResponse
import kotlinx.coroutines.flow.Flow

class DashboardRepository(
    private val ktorClient: KtorClient,
    private val prefs: PreferencesRepositoryImpl
){

    fun getToken(): Flow<String> {
        return prefs.getToken()
    }

    suspend fun getWallets(): Result<GetWalletsResponse, DataError.Remote> {
        return ktorClient.getWallets()
    }

    suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote> {
        return ktorClient.getCategories()
    }

    suspend fun getTransactions(): Result<GetTransactionsResponse, DataError.Remote> {
        return ktorClient.getTransactions()
    }

    fun getUserId(): Flow<String> {
        return prefs.getUserId()
    }

}