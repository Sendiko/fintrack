package id.my.sendiko.fintrack.wallet.create.presentation

sealed interface CreateWalletEvent {
    data class OnNameChanged(val name: String): CreateWalletEvent
    data class OnTypeChanged(val type: String): CreateWalletEvent
    data class OnPurposeChanged(val purpose: String): CreateWalletEvent
    data class OnNumberChanged(val number: String): CreateWalletEvent
    data object OnNext: CreateWalletEvent
    data class OnNumberPressed(val number: String): CreateWalletEvent
    data object OnCreate: CreateWalletEvent
}