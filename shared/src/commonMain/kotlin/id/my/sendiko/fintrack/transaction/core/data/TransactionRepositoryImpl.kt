package id.my.sendiko.fintrack.transaction.core.data

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.core.data.datasource.TransactionDataSource
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction
import id.my.sendiko.fintrack.transaction.core.domain.TransactionRepository

class TransactionRepositoryImpl(
    private val dataSource: TransactionDataSource
) : TransactionRepository {

    override suspend fun getTransactions(): Result<List<Transaction>, DataError.Remote> {
        return when (val response = dataSource.getTransactions()) {
            is Result.Success -> Result.Success(response.data.transactions.map { it.toDomain() })
            is Result.Error -> Result.Error(response.error)
        }
    }

}