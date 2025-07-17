package id.my.sendiko.fintrack.wallet.create.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.errorToUiText
import id.my.sendiko.fintrack.wallet.core.data.WalletRepository
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateWalletViewModel(
    private val repository: WalletRepository
): ViewModel() {

    private val _userId = repository.getUserId()
    private val _token = repository.getToken()
    private val _state = MutableStateFlow(CreateWalletState())
    val state = combine(_userId, _token, _state) { userId, token, state ->
        state.copy(userId = userId, token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CreateWalletState())

    fun onEvent(event: CreateWalletEvent) {
        when (event) {
            is CreateWalletEvent.OnNameChanged -> _state.update { it.copy(name = event.name) }
            is CreateWalletEvent.OnPurposeChanged -> _state.update { it.copy(purpose = event.purpose) }
            is CreateWalletEvent.OnTypeChanged -> _state.update { it.copy(type = event.type) }
            is CreateWalletEvent.OnNumberPressed -> handleNumberPress(event.number)
            CreateWalletEvent.OnNext -> _state.update { it.copy(stage = 2) }
            CreateWalletEvent.OnCreate -> postWallet()
            is CreateWalletEvent.OnWalletNumberChanged -> _state.update { it.copy(number = event.number) }
            CreateWalletEvent.OnBackspace -> handleBackspace()
        }
    }

    private fun postWallet() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val userId = state.value.userId
            val token = state.value.token
            val wallet = Wallet(
                id = "",
                name = state.value.name,
                purpose = state.value.purpose,
                type = state.value.type,
                amount = state.value.amount.toDouble(),
                number = state.value.number.toString()
            )
            repository
                .createWallet(token, userId, wallet)
                .onSuccess { result ->
                    _state.update { it.copy(
                        message = result.message,
                        isLoading = false,
                        success = true
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        message = errorToUiText(error),
                        isLoading = false,
                        error = true
                    ) }
                }
        }
    }

    private fun handleNumberPress(number: String) {
        if (_state.value.amount == "0.0") {
            _state.update { it.copy(amount = number) }
        } else _state.update { it.copy(amount = it.amount + number) }
    }

    private fun handleBackspace() {
        if (state.value.amount != "0.0") {
            _state.update { it.copy(amount = it.amount.dropLast(1)) }
        } else _state.update { it.copy(amount = "0.0") }
    }

}