package id.my.sendiko.fintrack.category.domain

import id.my.sendiko.fintrack.transaction.domain.Transaction

data class CategoryWithTransactions(
    val category: Category,
    val transactions: List<Transaction>
)
