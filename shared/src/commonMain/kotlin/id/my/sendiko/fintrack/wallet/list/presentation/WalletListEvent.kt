package id.my.sendiko.fintrack.wallet.list.presentation

sealed interface WalletListEvent {
    data object OnLoadData : WalletListEvent
    data class OnShowDeleteDialog(val walletId: String) : WalletListEvent
    data object OnDismissDeleteDialog : WalletListEvent
    data object OnDelete : WalletListEvent
    data class OnBalanceViewChanged(val isVisible: Boolean) : WalletListEvent
}