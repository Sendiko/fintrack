package id.my.sendiko.fintrack.budget.presentation.detail.presentation

sealed interface DetailBudgetEvent {
    data object OnRefresh: DetailBudgetEvent
}