package id.my.sendiko.fintrack.transaction.core.data

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferenceRepository
import id.my.sendiko.fintrack.transaction.core.data.datasource.TransactionDataSource
import id.my.sendiko.fintrack.transaction.core.data.dto.PostTransactionRequest
import id.my.sendiko.fintrack.transaction.core.domain.TransactionRepository
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val dataSource: TransactionDataSource,
    private val preferences: PreferenceRepository
) : TransactionRepository {

    override suspend fun getTransactions(): Result<List<Transaction>, DataError.Remote> {
        return when (val response = dataSource.getTransactions()) {
            is Result.Success -> Result.Success(response.data.transactions.map { it.toDomain() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun postTransaction(
        userId: String,
        categoryId: String,
        walletId: String,
        amount: Int,
        name: String,
        type: String
    ): Result<Transaction, DataError.Remote> {
        val request = PostTransactionRequest(
            walletId = walletId,
            amount = amount,
            name = name,
            type = type,
            userId = userId,
            categoryId = categoryId
        )
        return when (val response = dataSource.postTransaction(request)) {
            is Result.Success -> Result.Success(response.data.transaction.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override fun getUserId(): Flow<String> {
        return preferences.getUserId()
    }

}