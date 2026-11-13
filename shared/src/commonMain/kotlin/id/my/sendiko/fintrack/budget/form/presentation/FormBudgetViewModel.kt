package id.my.sendiko.fintrack.budget.form.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.budget_created
import id.my.sendiko.fintrack.budget.core.domain.BudgetRepository
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.UiText
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import id.my.sendiko.fintrack.wallet.core.domain.WalletRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormBudgetViewModel(
    private val budgetRepository: BudgetRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FormBudgetState())
    val state = _state.asStateFlow()

    fun onEvent(event: FormBudgetEvent) {
        when (event) {
            FormBudgetEvent.OnLoadWallets -> loadWallets()
            is FormBudgetEvent.OnWalletChosen -> chooseWallet(event.wallet)
            is FormBudgetEvent.OnStartDatePickerClicked -> showStartDatePicker(event.visible)
            is FormBudgetEvent.OnEndDatePickerClicked -> showEndDatePicker(event.visible)
            is FormBudgetEvent.OnStartDateConfirmed -> onStartDateConfirmed(event.date)
            is FormBudgetEvent.OnEndDateConfirmed -> onEndDateConfirmed(event.date)
            is FormBudgetEvent.OnAmountChanged -> onAmountChanged(event.amount)
            is FormBudgetEvent.OnNumberPressed -> handleNumberPress(event.number)
            FormBudgetEvent.OnBackspace -> handleBackspace()
            FormBudgetEvent.OnSave -> onSave()
        }
    }

    private fun chooseWallet(wallet: Wallet) = _state.update {
        it.copy(selectedWallet = wallet)
    }

    private fun setLoading(isLoading: Boolean) = _state.update {
        it.copy(isLoading = isLoading)
    }

    private fun loadWallets() = viewModelScope.launch {
        walletRepository.getWallets()
            .onSuccess { result ->
                _state.update { it.copy(wallets = result) }
            }
            .onError { error ->
                _state.update { it.copy(message = error.asUiText()) }
            }
        setLoading(false)
    }

    private fun showStartDatePicker(visible: Boolean) {
        _state.update { it.copy(isStartDatePickerOpen = visible) }
    }

    private fun showEndDatePicker(visible: Boolean) {
        _state.update { it.copy(isEndDatePickerOpen = visible) }
    }

    private fun onStartDateConfirmed(date: String) {
        _state.update { it.copy(startDate = date) }
    }

    private fun onEndDateConfirmed(date: String) {
        _state.update { it.copy(endDate = date) }
    }

    private fun onAmountChanged(amount: String) {
        _state.update { it.copy(amount = amount) }
    }

    private fun handleNumberPress(number: String) {
        if (_state.value.amount == "0.0") {
            _state.update { it.copy(amount = number) }
        } else {
            _state.update { it.copy(amount = it.amount + number) }
        }
    }

    private fun handleBackspace() {
        if (state.value.amount != "0.0") {
            val newAmount = state.value.amount.dropLast(1)
            _state.update { it.copy(amount = newAmount.ifEmpty { "0.0" }) }
        } else {
            _state.update { it.copy(amount = "0.0") }
        }
    }

    private fun onSave() = viewModelScope.launch {
        setLoading(true)
        if (state.value.selectedWallet != null) {
            budgetRepository.postBudget(
                amount = state.value.amount.toInt(),
                startDate = state.value.startDate,
                endDate = state.value.endDate,
                walletId = state.value.selectedWallet!!.id
            )
                .onSuccess {
                    _state.update { it.copy(
                        isSuccess = true,
                        message = UiText.CmpStringResource(Res.string.budget_created)
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        message = error.asUiText()
                    ) }
                }
        }
        setLoading(false)
    }

}
