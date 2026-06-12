package id.my.sendiko.fintrack.category.core.domain.model

import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction

data class CategoryWithTransactions(
    val category: Category,
    val transactions: List<Transaction>
)
