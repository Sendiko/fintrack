package id.my.sendiko.fintrack.budget.presentation.form.presentation

data class FormBudgetState(
    val isLoading: Boolean = false,
    val message: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val isStartDatePickerOpen: Boolean = false,
    val isEndDatePickerOpen: Boolean = false,
    val amount: String = "0.0",
)
