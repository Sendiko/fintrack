package id.my.sendiko.fintrack.wallet.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.wallet.core.data.WalletRepositoryImpl
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class WalletListViewModel(
    private val repository: WalletRepositoryImpl
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(WalletListState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(token = token, userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WalletListState())

    fun onEvent(event: WalletListEvent) {
        when (event) {
            WalletListEvent.OnLoadData -> loadData()
            is WalletListEvent.OnBalanceViewChanged -> changeBalanceView(event.isVisible)
            is WalletListEvent.OnShowDeleteDialog -> showDeleteDialog(event.walletId)
            WalletListEvent.OnDismissDeleteDialog -> dismissDeleteDialog()
            WalletListEvent.OnDelete -> deleteWallet()
        }
    }

    private fun setLoading(loading: Boolean) {
        _state.update { it.copy(isLoading = loading) }
    }

    private fun showDeleteDialog(walletId: String) {
        _state.update { it.copy(showDeleteDialog = true, walletId = walletId) }
    }

    private fun dismissDeleteDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

    private suspend fun clearState() {
        delay(2.seconds)
        _state.update {
            it.copy(
                isLoading = false,
                message = ""
            )
        }
    }

    private fun changeBalanceView(visible: Boolean) {
        _state.update { it.copy(balanceVisible = visible) }
    }

    private fun loadData() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getWallets()
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            wallets = result,
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(message = error.asUiText().asString())
                    }
                }
            setLoading(false)
        }
    }

    private fun deleteWallet() {
        viewModelScope.launch {
            setLoading(true)
            dismissDeleteDialog()
            repository.deleteWallet(state.value.walletId)
                .onSuccess { result ->
                    _state.update { it.copy(message = result) }
                }
                .onError { error ->
                    _state.update { it.copy(message = error.asUiText().asString()) }
                }
            loadData()
        }
    }

}