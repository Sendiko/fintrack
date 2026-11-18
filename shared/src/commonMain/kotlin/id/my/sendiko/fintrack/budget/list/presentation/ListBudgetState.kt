package id.my.sendiko.fintrack.budget.list.presentation

import id.my.sendiko.fintrack.budget.core.domain.model.Budget
import id.my.sendiko.fintrack.core.presentation.UiText

data class ListBudgetState(
    val isLoading: Boolean = true,
    val message: UiText = UiText.DynamicString(""),
    val budgets: List<Budget> = emptyList()
)
