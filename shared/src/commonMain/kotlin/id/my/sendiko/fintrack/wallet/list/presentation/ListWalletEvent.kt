package id.my.sendiko.fintrack.wallet.list.presentation

sealed interface ListWalletEvent {
    data object OnLoadData : ListWalletEvent
    data class OnShowDeleteDialog(val walletId: String) : ListWalletEvent
    data object OnDismissDeleteDialog : ListWalletEvent
    data object OnDelete : ListWalletEvent
    data class OnBalanceViewChanged(val isVisible: Boolean) : ListWalletEvent
}