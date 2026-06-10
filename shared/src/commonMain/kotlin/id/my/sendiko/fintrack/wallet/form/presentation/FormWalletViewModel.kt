package id.my.sendiko.fintrack.wallet.form.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.create_wallet_success_message
import fintrack.composeapp.generated.resources.update_wallet_success_message
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import id.my.sendiko.fintrack.wallet.core.domain.WalletRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import kotlin.time.Duration.Companion.seconds

class FormWalletViewModel(
    private val repository: WalletRepository
) : ViewModel() {

    private val _userId = repository.getUserId()
    private val _token = repository.getToken()
    private val _state = MutableStateFlow(FormWalletState())
    val state = combine(_userId, _token, _state) { userId, token, state ->
        state.copy(userId = userId, token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FormWalletState())

    fun onEvent(event: FormWalletEvent) {
        when (event) {
            FormWalletEvent.OnLoadWallet -> loadWallet()
            is FormWalletEvent.OnNameChanged -> _state.update { it.copy(name = event.name) }
            is FormWalletEvent.OnPurposeChanged -> _state.update { it.copy(purpose = event.purpose) }
            is FormWalletEvent.OnTypeChanged -> _state.update { it.copy(type = event.type) }
            is FormWalletEvent.OnNumberPressed -> handleNumberPress(event.number)
            FormWalletEvent.OnNext -> _state.update { it.copy(stage = 2) }
            FormWalletEvent.OnSave -> postWallet()
            is FormWalletEvent.OnWalletNumberChanged -> _state.update { it.copy(number = event.number) }
            FormWalletEvent.OnBackspace -> handleBackspace()
            FormWalletEvent.OnShowDeleteDialog -> showDeleteDialog()
            FormWalletEvent.OnDismissDeleteDialog -> dismissDeleteDialog()
            FormWalletEvent.OnDelete -> deleteWallet()
        }
    }

    private fun loadWallet() {
        viewModelScope.launch {
            setLoading(true)
            repository.getWallet(state.value.walletId)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            name = result.name,
                            type = result.type,
                            purpose = result.purpose,
                            number = result.number,
                            amount = result.amount.toString()
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            message = error.asUiText().asString(),
                            isError = true
                        )
                    }
                }
            setLoading(false)
        }
    }

    fun setId(id: String) {
        _state.update { it.copy(walletId = id) }
    }

    private fun setLoading(loading: Boolean) {
        _state.update { it.copy(isLoading = loading) }
    }

    private fun showDeleteDialog() {
        _state.update { it.copy(showDeleteDialog = true) }
    }

    private fun dismissDeleteDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

    private suspend fun clearState() {
        delay(3.seconds)
        _state.update {
            it.copy(
                isLoading = false,
                isSuccess = false,
                isError = false,
                message = ""
            )
        }
    }

    private fun postWallet() {
        setLoading(true)
        viewModelScope.launch {
            val userId = state.value.userId
            val wallet = Wallet(
                id = "",
                name = state.value.name,
                purpose = state.value.purpose,
                type = state.value.type,
                amount = state.value.amount.toDouble(),
                number = state.value.number
            )
            if (state.value.walletId.isNotBlank()) {
                repository
                    .updateWallet(userId, wallet)
                    .onSuccess {
                        _state.update {
                            it.copy(
                                message = getString(Res.string.update_wallet_success_message),
                                isLoading = false,
                                isSuccess = true
                            )
                        }
                    }
                    .onError { error ->
                        _state.update {
                            it.copy(
                                message = error.asUiText().asString(),
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
            } else {
                repository
                    .createWallet(userId, wallet)
                    .onSuccess {
                        _state.update {
                            it.copy(
                                message = getString(Res.string.create_wallet_success_message),
                                isLoading = false,
                                isSuccess = true
                            )
                        }
                    }
                    .onError { error ->
                        _state.update {
                            it.copy(
                                message = error.asUiText().asString(),
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
            }
            setLoading(false)
            clearState()
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

    private fun deleteWallet() {
        viewModelScope.launch {
            setLoading(true)
            dismissDeleteDialog()
            repository.deleteWallet(state.value.walletId)
                .onSuccess { result ->
                    _state.update { it.copy(message = result, isSuccess = true) }
                }
                .onError { error ->
                    _state.update { it.copy(isError = true, message = error.asUiText().asString()) }
                }
            clearState()
        }
    }

}