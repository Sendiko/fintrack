package id.my.sendiko.fintrack.transaction.form.presentation

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

data class FormTransactionState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val message: String = "",
    val reviewReceipt: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val showCategoriesBottomSheet: Boolean = false,
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val transactionId: String = "",
    val wallets: List<Wallet> = emptyList(),
    val categories: List<CategoryWithTransactions> = emptyList(),
    val userId: String = "",
    val stage: Int = 1,
    val selectedWallet: Wallet? = null,
    val selectedCategory: Category? = null,
    val selectedType: TransactionType = TransactionType.INCOME,
    val name: String = "",
    val amount: Double = 0.0,
    val transactionDate: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
)
