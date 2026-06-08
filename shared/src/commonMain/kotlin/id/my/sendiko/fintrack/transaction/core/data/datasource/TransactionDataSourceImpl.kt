package id.my.sendiko.fintrack.transaction.core.data.datasource

import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.data.dto.GetTransactionsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class TransactionDataSourceImpl(
    private val client: HttpClient
) : TransactionDataSource {
    override suspend fun getTransactions(): Result<GetTransactionsResponse, DataError.Remote> {
        return safeCall<GetTransactionsResponse> {
            client.get("transactions")
        }
    }
}