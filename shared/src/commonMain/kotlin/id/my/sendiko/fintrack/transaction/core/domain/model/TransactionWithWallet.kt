package id.my.sendiko.fintrack.transaction.core.domain.model

import id.my.sendiko.fintrack.wallet.core.domain.Wallet

data class TransactionWithWallet(
    val transaction: Transaction,
    val wallet: Wallet,
)