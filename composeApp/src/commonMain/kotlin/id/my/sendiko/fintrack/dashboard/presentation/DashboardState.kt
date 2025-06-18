package id.my.sendiko.fintrack.dashboard.presentation

import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.wallet.domain.Wallet

data class DashboardState(
    val isLoading: Boolean = false,
    val message: String = "",
    val userId: String = "",
    val email: String = "",
    val totalBalance: Double = 0.0,
    val wallets: List<Wallet> = id.my.sendiko.fintrack.dashboard.data.wallets,
    val categories: List<Category> = id.my.sendiko.fintrack.dashboard.data.categories,
    val transactions: List<Transaction> = id.my.sendiko.fintrack.dashboard.data.transactions,
    val balanceVisible: Boolean = false,
)
