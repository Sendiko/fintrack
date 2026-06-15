package id.my.sendiko.fintrack.transaction.form.presentation

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

sealed interface FormTransactionEvent {

    data class ProcessSharedImage(val imageBytes: ByteArray) : FormTransactionEvent {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as ProcessSharedImage

            return imageBytes.contentEquals(other.imageBytes)
        }

        override fun hashCode(): Int {
            return imageBytes.contentHashCode()
        }
    }

    data object HideOverlay: FormTransactionEvent

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