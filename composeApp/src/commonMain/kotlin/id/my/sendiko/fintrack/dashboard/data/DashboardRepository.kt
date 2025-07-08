package id.my.sendiko.fintrack.dashboard.data

import id.my.sendiko.fintrack.core.network.KtorClient
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferencesRepositoryImpl
import id.my.sendiko.fintrack.dashboard.data.dto.GetCategoriesResponse
import id.my.sendiko.fintrack.dashboard.data.dto.GetWalletsResponse
import kotlinx.coroutines.flow.Flow

class DashboardRepository(
    private val ktorClient: KtorClient,
    private val prefs: PreferencesRepositoryImpl
){

    fun getToken(): Flow<String> {
        return prefs.getToken()
    }

    suspend fun getWallets(token: String): Result<GetWalletsResponse, DataError.Remote> {
        return ktorClient.getWallets(token)
    }

    suspend fun getCategories(token: String): Result<GetCategoriesResponse, DataError.Remote> {
        return ktorClient.getCategories(token)
    }
}