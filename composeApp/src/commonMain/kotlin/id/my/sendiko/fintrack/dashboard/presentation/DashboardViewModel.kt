package id.my.sendiko.fintrack.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.errorToUiText
import id.my.sendiko.fintrack.dashboard.data.DashboardRepository
import id.my.sendiko.fintrack.wallet.domain.Wallet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: DashboardRepository
): ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(DashboardState())
    val state = combine(_state, _token) { state, token ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardState())

    fun onEvent(event: DashboardEvent) {
        when(event) {
            DashboardEvent.OnLoadData -> fetchData()
            DashboardEvent.ClearState -> clearState()
            is DashboardEvent.OnBalanceViewChanged -> changeBalanceVisibility(event.isVisible)
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getWallets(state.value.token)
                .onSuccess { result ->
                    val wallets = result.wallets.map { walletsItem ->
                        Wallet(
                            id = walletsItem.id,
                            name = walletsItem.name,
                            purpose = walletsItem.purpose,
                            type = walletsItem.type,
                            amount = walletsItem.balance.toDouble(),
                            number = walletsItem.walletNumber ?: ""
                        )
                    }
                    _state.update {
                        it.copy(
                            wallets = wallets,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            message = errorToUiText(error),
                            isLoading = false
                        )
                    }
                }
            repository.getCategories(state.value.token)
                .onSuccess { result ->
                    val categories = result.categories.map { category ->
                        Category(
                            id = category.id,
                            name = category.name
                        )
                    }
                    _state.update {
                        it.copy(
                            categories = categories,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            message = errorToUiText(error),
                            isLoading = false
                        )
                    }
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