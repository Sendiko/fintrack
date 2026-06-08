package id.my.sendiko.fintrack.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.category.domain.model.TopCategory
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.dashboard.domain.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(DashboardState())
    val state = combine(_state, _userId) { state, userId ->
        state.copy(userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardState())

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.OnLoadData -> fetchData()
            DashboardEvent.ClearState -> clearState()
            is DashboardEvent.OnBalanceViewChanged -> changeBalanceVisibility(event.isVisible)
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getWallets()
            getCategories()
            getTransactions()
            _state.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun getTransactions() {
        repository.getTransactions()
            .onSuccess { result ->
                result.groupBy { item -> item.categoryId }
                _state.update { it.copy(transactions = result) }
            }
    }

    private suspend fun getCategories() {
        repository.getCategories()
            .onSuccess { result ->
                val topCategory = result
                    .map { category ->
                        val totalAmount = category.transactions.sumOf { it.amount.toDouble() }
                        category.category to totalAmount
                    }
                    .sortedByDescending { it.second }
                    .take(2)
                    .map { (category, totalAmount) ->
                        TopCategory(
                            name = category.name,
                            totalAmount = totalAmount
                        )
                    }
                _state.update { state ->
                    state.copy(
                        categories = result.map { it.category },
                        topCategory = topCategory
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(message = error.asUiText().asString())
                }
            }
    }

    private suspend fun getWallets() {
        repository.getWallets()
            .onSuccess { result ->
                _state.update {
                    it.copy(wallets = result)
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(message = error.asUiText().asString())
                }
            }
    }

    private fun clearState() {
        _state.update { it.copy(isLoading = false, message = "") }
    }

    private fun changeBalanceVisibility(visible: Boolean) {
        _state.update { it.copy(balanceVisible = visible) }
    }

}