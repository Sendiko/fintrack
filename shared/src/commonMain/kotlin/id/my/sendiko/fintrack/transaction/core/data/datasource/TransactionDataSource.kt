package id.my.sendiko.fintrack.transaction.core.data.datasource

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.data.dto.GetTransactionsResponse
import id.my.sendiko.fintrack.transaction.core.data.dto.PostTransactionRequest
import id.my.sendiko.fintrack.transaction.core.data.dto.PostTransactionResponse

interface TransactionDataSource {

    suspend fun getTransactions(): Result<GetTransactionsResponse, DataError.Remote>

    suspend fun postTransaction(request: PostTransactionRequest): Result<PostTransactionResponse, DataError.Remote>

}