package id.my.sendiko.fintrack.wallet.create.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateWalletViewModel: ViewModel() {

    private val _state = MutableStateFlow(CreateWalletState())
    val state = _state.asStateFlow()

    fun onEvent(event: CreateWalletEvent) {
        when (event) {
            is CreateWalletEvent.OnNameChanged -> _state.update { it.copy(name = event.name) }
            is CreateWalletEvent.OnPurposeChanged -> _state.update { it.copy(purpose = event.purpose) }
            is CreateWalletEvent.OnTypeChanged -> _state.update { it.copy(type = event.type) }
            is CreateWalletEvent.OnNumberPressed -> handleNumberPress(event.number)
            CreateWalletEvent.OnNext -> _state.update { it.copy(stage = 2) }
            CreateWalletEvent.OnCreate -> { /* TODO: Implement wallet creation logic */ }
            is CreateWalletEvent.OnWalletNumberChanged -> TODO()
            CreateWalletEvent.OnBackspace -> handleBackspace()
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