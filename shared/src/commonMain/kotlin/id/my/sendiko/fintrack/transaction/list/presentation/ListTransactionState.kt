package id.my.sendiko.fintrack.transaction.list.presentation

import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionWithCategoryAndWallet

data class ListTransactionState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val message: String = "",
    val transactions: List<TransactionWithCategoryAndWallet> = emptyList(),
    val selectedType: TransactionType? = null,
)
