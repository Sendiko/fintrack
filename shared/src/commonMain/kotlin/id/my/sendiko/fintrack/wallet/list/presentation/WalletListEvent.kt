package id.my.sendiko.fintrack.wallet.list.presentation

sealed interface WalletListEvent {
    data object OnLoadData: WalletListEvent
    data class OnBalanceViewChanged(val isVisible: Boolean): WalletListEvent
}