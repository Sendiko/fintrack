package id.my.sendiko.fintrack.transaction.form.presentation

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

data class FormTransactionState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val message: String = "",
    val showDeleteDialog: Boolean = false,
    val transactionId: String = "",
    val wallets: List<Wallet> = emptyList(),
    val categories: List<CategoryWithTransactions> = emptyList(),
    val userId: String = "",
    val stage: Int = 1,
    val selectedWallet: Wallet? = null,
    val selectedCategory: Category? = null,
    val selectedType: TransactionType = TransactionType.INCOME,
    val name: String = "",
    val amount: String = "0.0"
)
