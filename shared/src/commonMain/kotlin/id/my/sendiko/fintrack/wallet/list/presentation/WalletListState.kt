package id.my.sendiko.fintrack.wallet.list.presentation

import id.my.sendiko.fintrack.wallet.core.domain.Wallet

data class WalletListState(
    val token: String = "",
    val isLoading: Boolean = false,
    val message: String = "",
    val userId: String = "",
    val wallets: List<Wallet> = emptyList(),
    val balanceVisible: Boolean = false,
)
