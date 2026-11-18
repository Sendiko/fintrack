package id.my.sendiko.fintrack.budget.form.presentation

import id.my.sendiko.fintrack.core.presentation.UiText
import id.my.sendiko.fintrack.wallet.core.domain.Wallet

data class FormBudgetState(
    val isLoading: Boolean = true,
    val message: UiText = UiText.DynamicString(""),
    val isSuccess: Boolean = false,
    val startDate: String = "",
    val endDate: String = "",
    val isStartDatePickerOpen: Boolean = false,
    val isEndDatePickerOpen: Boolean = false,
    val amount: String = "0.0",
    val wallets: List<Wallet> = emptyList(),
    val selectedWallet: Wallet? = null
)
