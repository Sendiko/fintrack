package id.my.sendiko.fintrack.transaction.form.presentation

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.datetime.LocalDateTime

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
    data object ShowCategoriesBottomSheet: FormTransactionEvent
    data object DismissCategoriesBottomSheet: FormTransactionEvent
    data object ShowDatePicker: FormTransactionEvent
    data object DismissDatePicker: FormTransactionEvent
    data object ShowTimePicker: FormTransactionEvent
    data object DismissTimePicker: FormTransactionEvent
    data class OnNameChanged(val name: String) : FormTransactionEvent
    data class OnTypeChanged(val type: String) : FormTransactionEvent
    data class OnWalletChanged(val wallet: Wallet): FormTransactionEvent
    data class OnCategoryChanged(
        val category: Category,
        val moveToFirst: Boolean = false,
    ) : FormTransactionEvent
    data class OnAmountChanged(val amount: Double) : FormTransactionEvent
    data class OnTransactionDateChanged(val dateTime: LocalDateTime) : FormTransactionEvent
    data object OnSave : FormTransactionEvent
    data object OnDelete: FormTransactionEvent

}