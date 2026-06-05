package id.my.sendiko.fintrack.transaction.domain

data class Transaction(
    val id: String,
    val name: String,
    val amount: Float,
    val type: TransactionType,
    val categoryId: String,
    val userId: String,
    val walletId: String,
)