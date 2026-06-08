package id.my.sendiko.fintrack.transaction.core.domain

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction

interface TransactionRepository {

    suspend fun getTransactions(): Result<List<Transaction>, DataError.Remote>

}