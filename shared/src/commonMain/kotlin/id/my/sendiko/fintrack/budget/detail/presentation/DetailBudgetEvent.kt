package id.my.sendiko.fintrack.budget.detail.presentation

sealed interface DetailBudgetEvent {
    data class OnRefresh(val budgetId: String): DetailBudgetEvent
}