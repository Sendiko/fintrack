package id.my.sendiko.fintrack.transaction.create.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.choose_category_error
import fintrack.composeapp.generated.resources.choose_wallet_error
import fintrack.composeapp.generated.resources.set_amount_error
import fintrack.composeapp.generated.resources.set_name_error
import id.my.sendiko.fintrack.category.domain.CategoryRepository
import id.my.sendiko.fintrack.category.domain.model.Category
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.transaction.core.domain.TransactionRepository
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
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

class CreateTransactionViewModel(
    private val repository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(CreateTransactionState())
    val state = combine(_state, _userId) { state, userId ->
        state.copy(userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CreateTransactionState())

    fun onEvent(event: CreateTransactionEvent) {
        when (event) {
            CreateTransactionEvent.LoadData -> loadData()
            is CreateTransactionEvent.OnWalletChanged -> changeWallet(event.wallet)
            is CreateTransactionEvent.OnTypeChanged -> setType(event.type)
            is CreateTransactionEvent.OnCategoryChanged -> changeCategory(event.category)
            is CreateTransactionEvent.OnNameChanged -> changeName(event.name)
            CreateTransactionEvent.OnNext -> onNext()
            is CreateTransactionEvent.OnNumberPressed -> handleNumberPress(event.number)
            CreateTransactionEvent.OnBackspace -> handleBackspace()
            CreateTransactionEvent.OnCreate -> createTransaction()
        }
    }

    private suspend fun clearState() {
        delay(2.seconds)
        setLoading(false)
        _state.update {
            it.copy(
                isSuccess = false,
                isError = false,
                message = "",
            )
        }
    }

    private fun setLoading(loading: Boolean) {
        _state.update { it.copy(isLoading = loading) }
    }

    private fun loadData() {
        viewModelScope.launch {
            setLoading(true)
            walletRepository.getWallets()
                .onSuccess { result ->
                    _state.update { it.copy(wallets = result) }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isError = true,
                            message = error.asUiText().asString()
                        )
                    }
                }
            categoryRepository.getCategories()
                .onSuccess { result ->
                    _state.update { it.copy(categories = result) }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isError = true,
                            message = error.asUiText().asString()
                        )
                    }
                }
            setLoading(false)
        }
    }

    private fun changeName(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun setType(type: String) {
        _state.update { it.copy(selectedType = TransactionType.valueOf(type)) }
    }

    private fun changeWallet(wallet: Wallet) {
        _state.update { it.copy(selectedWallet = wallet) }
    }

    private fun changeCategory(category: Category) {
        _state.update { it.copy(selectedCategory = category) }
    }

    private fun onNext() {
        _state.update { it.copy(stage = it.stage + 1) }
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

    private fun createTransaction() {
        viewModelScope.launch {
            if (state.value.selectedWallet == null) {
                _state.update { it.copy(message = getString(Res.string.choose_wallet_error)) }
                return@launch
            }
            if (state.value.selectedCategory == null) {
                _state.update { it.copy(message = getString(Res.string.choose_category_error)) }
                return@launch
            }
            if (state.value.name.isBlank()) {
                _state.update { it.copy(message = getString(Res.string.set_name_error)) }
                return@launch
            }
            setLoading(true)
            repository.postTransaction(
                userId = state.value.userId,
                categoryId = state.value.selectedCategory?.id.toString(),
                walletId = state.value.selectedWallet?.id.toString(),
                amount = state.value.amount.toInt(),
                name = state.value.name,
                type = state.value.selectedType.name
            )
                .onSuccess {
                    _state.update {
                        it.copy(
                            message = getString(Res.string.set_amount_error),
                            isSuccess = true
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
            clearState()
        }
    }

}