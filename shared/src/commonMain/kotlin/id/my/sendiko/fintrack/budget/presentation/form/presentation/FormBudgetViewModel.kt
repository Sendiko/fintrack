package id.my.sendiko.fintrack.budget.presentation.form.presentation

import androidx.lifecycle.ViewModel
import id.my.sendiko.fintrack.budget.domain.BudgetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormBudgetViewModel(
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FormBudgetState())
    val state = _state.asStateFlow()

    fun onEvent(event: FormBudgetEvent) {
        when (event) {
            is FormBudgetEvent.OnStartDatePickerClicked -> showStartDatePicker(event.visible)
            is FormBudgetEvent.OnEndDatePickerClicked -> showEndDatePicker(event.visible)
            is FormBudgetEvent.OnStartDateConfirmed -> onStartDateConfirmed(event.date)
            is FormBudgetEvent.OnEndDateConfirmed -> onEndDateConfirmed(event.date)
            is FormBudgetEvent.OnAmountChanged -> onAmountChanged(event.amount)
            is FormBudgetEvent.OnNumberPressed -> handleNumberPress(event.number)
            FormBudgetEvent.OnBackspace -> handleBackspace()
            FormBudgetEvent.OnSave -> onSave()
        }
    }

    private fun showStartDatePicker(visible: Boolean) {
        _state.update { it.copy(isStartDatePickerOpen = visible) }
    }

    private fun showEndDatePicker(visible: Boolean) {
        _state.update { it.copy(isEndDatePickerOpen = visible) }
    }

    private fun onStartDateConfirmed(date: String) {
        _state.update { it.copy(startDate = date) }
    }

    private fun onEndDateConfirmed(date: String) {
        _state.update { it.copy(endDate = date) }
    }

    private fun onAmountChanged(amount: String) {
        _state.update { it.copy(amount = amount) }
    }

    private fun handleNumberPress(number: String) {
        if (_state.value.amount == "0.0") {
            _state.update { it.copy(amount = number) }
        } else {
            _state.update { it.copy(amount = it.amount + number) }
        }
    }

    private fun handleBackspace() {
        if (state.value.amount != "0.0") {
            val newAmount = state.value.amount.dropLast(1)
            _state.update { it.copy(amount = if (newAmount.isEmpty()) "0.0" else newAmount) }
        } else {
            _state.update { it.copy(amount = "0.0") }
        }
    }

    private fun onSave() {

    }

}
