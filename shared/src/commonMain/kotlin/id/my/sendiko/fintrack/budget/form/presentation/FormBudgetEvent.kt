package id.my.sendiko.fintrack.budget.form.presentation

sealed interface FormBudgetEvent {
    data class OnStartDatePickerClicked(val visible: Boolean) : FormBudgetEvent
    data class OnEndDatePickerClicked(val visible: Boolean) : FormBudgetEvent
    data class OnStartDateConfirmed(val date: String) : FormBudgetEvent
    data class OnEndDateConfirmed(val date: String) : FormBudgetEvent
    data class OnAmountChanged(val amount: String) : FormBudgetEvent
    data class OnNumberPressed(val number: String) : FormBudgetEvent
    data object OnBackspace : FormBudgetEvent
    data object OnSave : FormBudgetEvent
}
