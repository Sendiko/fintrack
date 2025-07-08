package id.my.sendiko.fintrack.dashboard.presentation

import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.category.domain.TopCategory
import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.wallet.domain.Wallet

data class DashboardState(
    val token: String = "",
    val isLoading: Boolean = false,
    val message: String = "",
    val userId: String = "",
    val email: String = "",
    val totalBalance: Double = 0.0,
    val topCategory: List<TopCategory> = emptyList(),
    val wallets: List<Wallet> = emptyList(),
    val categories: List<Category> = emptyList(),
    val transactions: List<Transaction> = emptyList(),
    val balanceVisible: Boolean = false,
)
