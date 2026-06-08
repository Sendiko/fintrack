package id.my.sendiko.fintrack.dashboard.presentation

import id.my.sendiko.fintrack.category.domain.model.Category
import id.my.sendiko.fintrack.category.domain.model.TopCategory
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionWithCategoryAndWallet
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

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
    val transactions: List<TransactionWithCategoryAndWallet> = emptyList(),
    val balanceVisible: Boolean = false,
)
