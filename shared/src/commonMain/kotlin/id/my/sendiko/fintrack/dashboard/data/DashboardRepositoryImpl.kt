package id.my.sendiko.fintrack.dashboard.data

import id.my.sendiko.fintrack.category.core.data.datasource.CategoryDataSource
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferenceRepository
import id.my.sendiko.fintrack.dashboard.domain.DashboardRepository
import id.my.sendiko.fintrack.transaction.core.data.datasource.TransactionDataSource
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionWithCategoryAndWallet
import id.my.sendiko.fintrack.wallet.core.data.datasource.WalletDataSource
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.coroutines.flow.Flow

class DashboardRepositoryImpl(
    private val transactionDataSource: TransactionDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val walletDataSource: WalletDataSource,
    private val prefs: PreferenceRepository
) : DashboardRepository {

    override suspend fun getWallets(): Result<List<Wallet>, DataError.Remote> {
        return when (val response = walletDataSource.getWallets()) {
            is Result.Success -> Result.Success(response.data.wallets.map { it.toDomain() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun getCategories(): Result<List<CategoryWithTransactions>, DataError.Remote> {
        return when (val response = categoryDataSource.getCategories()) {
            is Result.Success -> Result.Success(response.data.categories.map { it.toDomainWithTransaction() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun getTransactions(): Result<List<TransactionWithCategoryAndWallet>, DataError.Remote> {
        return when (val response = transactionDataSource.getTransactions()) {
            is Result.Success -> Result.Success(response.data.transactions.map { it.toDomainWithCategoryAndWallet() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override fun getUserId(): Flow<String> {
        return prefs.getUserId()
    }

}