package id.my.sendiko.fintrack.dashboard.data

import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.transaction.domain.TransactionType
import id.my.sendiko.fintrack.wallet.domain.Wallet

val wallets = listOf(
    Wallet(
        id = "1",
        name = "Daily Expenses",
        purpose = "Expense Daily",
        type = "e-money",
        amount = 100000.0,
        number = "82241626760"
    ),
    Wallet(
        id = "2",
        name = "Gadget Savings",
        purpose = "Purchase Gadget",
        type = "card",
        amount = 50000.0,
        number = "123456789"
    ),
    Wallet(
        id = "3",
        name = "Investment",
        purpose = "Savings",
        type = "cash",
        amount = 200000.0,
        number = "123456789"
    ),
)

val categories = listOf(
    Category(
        id = "1",
        name = "Food",
    ),
    Category(
        id = "2",
        name = "Transportation",
    ),
    Category(
        id = "3",
        name = "Entertainment",
    ),
)

val transactions = listOf(
    Transaction(
        id = "1",
        name = "Jumpstart Caffe Latte",
        amount = 10000f,
        type = TransactionType.EXPENSE,
        categoryId = "1",
        userId = "1",
        walletId = "1"
    ),
    Transaction(
        id = "2",
        name = "Jagat Raya Chicken",
        amount = 15000f,
        type = TransactionType.EXPENSE,
        categoryId = "1",
        userId = "1",
        walletId = "1"
    ),
    Transaction(
        id = "3",
        name = "Disney+ Hotstar",
        amount = 30000f,
        type = TransactionType.EXPENSE,
        categoryId = "3",
        userId = "1",
        walletId = "1"
    ),
)