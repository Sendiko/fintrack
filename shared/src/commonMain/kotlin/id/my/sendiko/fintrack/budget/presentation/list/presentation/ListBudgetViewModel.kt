package id.my.sendiko.fintrack.budget.presentation.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.budget.domain.BudgetRepository
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListBudgetViewModel(
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListBudgetState())
    val state = _state.asStateFlow()

    init {
        fetchBudgets()
    }

    private fun fetchBudgets() = viewModelScope.launch {
        budgetRepository.getBudgets()
            .onSuccess { result ->
                _state.update { it.copy(budgets = result) }
            }
            .onError { error ->
                _state.update { it.copy(message = error.asUiText()) }
            }
    }

}