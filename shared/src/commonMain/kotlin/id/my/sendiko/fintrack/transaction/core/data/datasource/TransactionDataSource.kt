package id.my.sendiko.fintrack.transaction.core.data.datasource

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.data.dto.GetTransactionsResponse

interface TransactionDataSource {

    suspend fun getTransactions(): Result<GetTransactionsResponse, DataError.Remote>

}