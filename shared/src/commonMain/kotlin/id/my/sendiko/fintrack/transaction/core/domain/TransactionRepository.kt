package id.my.sendiko.fintrack.transaction.core.domain

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun getTransactions(): Result<List<Transaction>, DataError.Remote>

    suspend fun postTransaction(
        userId: String,
        categoryId: String,
        walletId: String,
        amount: Int,
        name: String,
        type: String
    ): Result<Transaction, DataError.Remote>

    fun getUserId(): Flow<String>

}