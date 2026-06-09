package id.my.sendiko.fintrack.transaction.core.data.datasource

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.data.dto.DeleteTransactionResponse
import id.my.sendiko.fintrack.transaction.core.data.dto.GetTransactionResponse
import id.my.sendiko.fintrack.transaction.core.data.dto.GetTransactionsResponse
import id.my.sendiko.fintrack.transaction.core.data.dto.PostTransactionRequest
import id.my.sendiko.fintrack.transaction.core.data.dto.PostTransactionResponse
import id.my.sendiko.fintrack.transaction.core.data.dto.PutTransactionResponse

interface TransactionDataSource {

    suspend fun getTransactions(): Result<GetTransactionsResponse, DataError.Remote>

    suspend fun getTransaction(id: String): Result<GetTransactionResponse, DataError.Remote>

    suspend fun postTransaction(request: PostTransactionRequest): Result<PostTransactionResponse, DataError.Remote>

    suspend fun putTransaction(id: String, request: PostTransactionRequest): Result<PutTransactionResponse, DataError.Remote>

    suspend fun deleteTransaction(id: String): Result<DeleteTransactionResponse, DataError.Remote>

}