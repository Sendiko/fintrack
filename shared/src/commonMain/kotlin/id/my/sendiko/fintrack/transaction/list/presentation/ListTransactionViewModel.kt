package id.my.sendiko.fintrack.transaction.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.transaction.core.domain.TransactionRepository
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListTransactionViewModel(
    val repository: TransactionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListTransactionState())
    val state = _state.asStateFlow()

    fun onEvent(event: ListTransactionEvent) {
        when (event) {
            ListTransactionEvent.LoadData -> loadData()
            is ListTransactionEvent.OnTypeChanged -> filterData(event.type)
        }
    }

    private fun setLoading(loading: Boolean) {
        _state.update { it.copy(isLoading = loading) }
    }


    private fun loadData() {
        viewModelScope.launch {
            setLoading(true)
            repository.getTransactions()
                .onSuccess { result ->
                    _state.update { state -> state.copy(transactions = result.sortedByDescending { it.transaction.createdAt }) }
                }
                .onError { error ->
                    _state.update { it.copy(message = error.asUiText().asString(), isError = true) }
                }
            setLoading(false)
        }
    }

    private fun filterData(type: TransactionType?) {
        viewModelScope.launch {
            setLoading(true)
            repository.getTransactions()
                .onSuccess { result ->
                    _state.update { state ->
                        state.copy(
                            transactions = result.sortedByDescending { it.transaction.createdAt }
                                .filter { type == null || it.transaction.type == type },
                            selectedType = type
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(message = error.asUiText().asString(), isError = true) }
                }
            setLoading(false)
        }
    }

}