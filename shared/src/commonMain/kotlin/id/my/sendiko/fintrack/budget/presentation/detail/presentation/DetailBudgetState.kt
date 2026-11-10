package id.my.sendiko.fintrack.budget.presentation.detail.presentation

import id.my.sendiko.fintrack.budget.domain.model.Budget
import id.my.sendiko.fintrack.core.presentation.UiText

data class DetailBudgetState(
    val isLoading: Boolean = true,
    val message: UiText = UiText.DynamicString(""),
    val isRefreshing: Boolean = false,
    val budget: Budget? = null,
    val budgetId: String = "",
)
