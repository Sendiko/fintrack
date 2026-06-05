package id.my.sendiko.fintrack.wallet.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.errorToUiText
import id.my.sendiko.fintrack.wallet.core.data.WalletRepository
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WalletListViewModel(
    private val repository: WalletRepository
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
        }
    }

    private fun changeBalanceView(visible: Boolean) {
        _state.update { it.copy(balanceVisible = visible) }
    }

    private fun loadData() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getWallets(state.value.token)
                .onSuccess { result ->
                    val wallets = result.wallets.map { walletItem ->
                        Wallet(
                            id = walletItem.id,
                            name = walletItem.name,
                            purpose = walletItem.purpose,
                            type = walletItem.type,
                            amount = walletItem.balance.toDouble(),
                            number = walletItem.walletNumber ?: ""
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
                        it.copy(message = errorToUiText(error))
                    }
                }
        }
    }

}