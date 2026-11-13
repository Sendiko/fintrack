package id.my.sendiko.fintrack.budget.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.budget.core.domain.BudgetRepository
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailBudgetViewModel(
    private val repository: BudgetRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailBudgetState())
    val state = _state.asStateFlow()

    fun fetchBudget(budgetId: String) = viewModelScope.launch {
        _state.update { it.copy(budgetId = budgetId) }
        repository.getBudget(budgetId)
            .onSuccess { result ->
                _state.update { it.copy(budget = result) }
            }
            .onError { error ->
                _state.update { it.copy(message = error.asUiText()) }
            }
        _state.update { it.copy(isLoading = false) }
    }

}