package id.my.sendiko.fintrack.transaction.core.domain.model

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

data class TransactionWithCategoryAndWallet(
    val transaction: Transaction,
    val category: Category,
    val wallet: Wallet
)
