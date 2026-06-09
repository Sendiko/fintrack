package id.my.sendiko.fintrack.transaction.core.domain

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionWithCategoryAndWallet
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun getTransactions(): Result<List<TransactionWithCategoryAndWallet>, DataError.Remote>

    suspend fun getTransaction(id: String): Result<TransactionWithCategoryAndWallet, DataError.Remote>

    suspend fun postTransaction(
        userId: String,
        categoryId: String,
        walletId: String,
        amount: Int,
        name: String,
        type: String
    ): Result<Transaction, DataError.Remote>

    suspend fun putTransaction(
        transactionId: String,
        userId: String,
        categoryId: String,
        walletId: String,
        amount: Int,
        name: String,
        type: String
    ): Result<Transaction, DataError.Remote>

    suspend fun deleteTransaction(id: String,): Result<String, DataError.Remote>

    fun getUserId(): Flow<String>

}