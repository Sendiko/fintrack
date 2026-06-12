package id.my.sendiko.fintrack.transaction.form.presentation

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

sealed interface FormTransactionEvent {

    data object LoadData: FormTransactionEvent

    data object DismissDeleteDialog: FormTransactionEvent

    data object ShowDeleteDialog: FormTransactionEvent

    data class OnNameChanged(val name: String) : FormTransactionEvent

    data class OnTypeChanged(val type: String) : FormTransactionEvent

    data class OnWalletChanged(val wallet: Wallet): FormTransactionEvent

    data class OnCategoryChanged(val category: Category): FormTransactionEvent

    data object OnNext : FormTransactionEvent

    data object OnPrevious : FormTransactionEvent

    data class OnNumberPressed(val number: String) : FormTransactionEvent

    data object OnBackspace : FormTransactionEvent

    data object OnSave : FormTransactionEvent

    data object OnDelete: FormTransactionEvent

}