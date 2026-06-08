package id.my.sendiko.fintrack.transaction.create.presentation

import id.my.sendiko.fintrack.category.domain.model.Category
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

data class CreateTransactionState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val message: String = "",
    val wallets: List<Wallet> = emptyList(),
    val categories: List<Category> = emptyList(),
    val userId: String = "",
    val stage: Int = 1,
    val selectedWallet: Wallet? = null,
    val selectedCategory: Category? = null,
    val selectedType: TransactionType = TransactionType.INCOME,
    val name: String = "",
    val amount: String = "0.0"
)
