package id.my.sendiko.fintrack.dashboard.domain

import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionWithCategoryAndWallet
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun getWallets(): Result<List<Wallet>, DataError.Remote>

    suspend fun getCategories(): Result<List<CategoryWithTransactions>, DataError.Remote>

    suspend fun getTransactions(): Result<List<TransactionWithCategoryAndWallet>, DataError.Remote>

    fun getUserId(): Flow<String>

}