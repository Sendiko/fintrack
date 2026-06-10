package id.my.sendiko.fintrack.wallet.form.presentation

sealed interface FormWalletEvent {
    data class OnNameChanged(val name: String): FormWalletEvent
    data class OnTypeChanged(val type: String): FormWalletEvent
    data class OnPurposeChanged(val purpose: String): FormWalletEvent
    data class OnWalletNumberChanged(val number: String): FormWalletEvent
    data object OnNext: FormWalletEvent
    data class OnNumberPressed(val number: String): FormWalletEvent
    data object OnBackspace: FormWalletEvent
    data object OnSave: FormWalletEvent
}