package id.my.sendiko.fintrack.transaction.list.presentation

import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType

sealed interface ListTransactionEvent {

    data object LoadData: ListTransactionEvent

    data class OnTypeChanged(val type: TransactionType?): ListTransactionEvent

}