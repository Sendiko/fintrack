package id.my.sendiko.fintrack.transaction.create.presentation

import id.my.sendiko.fintrack.category.domain.model.Category
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

sealed interface CreateTransactionEvent {

    data object LoadData: CreateTransactionEvent

    data class OnNameChanged(val name: String) : CreateTransactionEvent

    data class OnTypeChanged(val type: String) : CreateTransactionEvent

    data class OnWalletChanged(val wallet: Wallet): CreateTransactionEvent

    data class OnCategoryChanged(val category: Category): CreateTransactionEvent

    data object OnNext : CreateTransactionEvent

    data object OnPrevious : CreateTransactionEvent

    data class OnNumberPressed(val number: String) : CreateTransactionEvent

    data object OnBackspace : CreateTransactionEvent

    data object OnCreate : CreateTransactionEvent

}