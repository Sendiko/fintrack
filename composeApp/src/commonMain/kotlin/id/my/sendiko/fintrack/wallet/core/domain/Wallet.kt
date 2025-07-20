package id.my.sendiko.fintrack.wallet.core.domain

data class Wallet(
    val id: String,
    val name: String,
    val purpose: String,
    val type: String,
    val amount: Double,
    val number: String,
)