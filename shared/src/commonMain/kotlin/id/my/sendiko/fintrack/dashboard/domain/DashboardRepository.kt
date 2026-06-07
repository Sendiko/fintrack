package id.my.sendiko.fintrack.dashboard.domain

import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.category.domain.CategoryWithTransactions
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

interface DashboardRepository {

    suspend fun getWallets(): Result<List<Wallet>, DataError.Remote>

    suspend fun getCategories(): Result<List<CategoryWithTransactions>, DataError.Remote>

    suspend fun getTransactions(): Result<List<Transaction>, DataError.Remote>

}