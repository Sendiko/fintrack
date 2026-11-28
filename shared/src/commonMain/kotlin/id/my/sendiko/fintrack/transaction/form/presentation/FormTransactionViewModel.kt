package id.my.sendiko.fintrack.transaction.form.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.choose_category_error
import fintrack.composeapp.generated.resources.choose_wallet_error
import fintrack.composeapp.generated.resources.set_amount_error
import fintrack.composeapp.generated.resources.set_name_error
import fintrack.composeapp.generated.resources.transaction_recorded
import id.my.sendiko.fintrack.category.core.domain.CategoryRepository
import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.transaction.core.domain.OcrEngine
import id.my.sendiko.fintrack.transaction.core.domain.ReceiptParser
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
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.compose.resources.getString
import kotlin.time.Duration.Companion.seconds

class FormTransactionViewModel(
    private val repository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val categoryRepository: CategoryRepository,
    private val ocrEngine: OcrEngine,
    private val parser: ReceiptParser
) : ViewModel() {

    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(FormTransactionState())
    val state = combine(_state, _userId) { state, userId ->
        state.copy(userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FormTransactionState())

    fun onEvent(event: FormTransactionEvent) {
        when (event) {
            FormTransactionEvent.LoadData -> loadData()
            FormTransactionEvent.ShowDeleteDialog -> showDialog()
            FormTransactionEvent.DismissDeleteDialog -> dismissDialog()
            FormTransactionEvent.ShowCategoriesBottomSheet -> showCategoriesBottomSheet()
            FormTransactionEvent.DismissCategoriesBottomSheet -> dismissCategoriesBottomSheet()
            FormTransactionEvent.ShowDatePicker -> showDatePicker()
            FormTransactionEvent.DismissDatePicker -> dismissDatePicker()
            FormTransactionEvent.ShowTimePicker -> showTimePicker()
            FormTransactionEvent.DismissTimePicker -> dismissTimePicker()
            is FormTransactionEvent.OnWalletChanged -> changeWallet(event.wallet)
            is FormTransactionEvent.OnTypeChanged -> setType(event.type)
            is FormTransactionEvent.OnCategoryChanged -> changeCategory(event.category, event.moveToFirst)
            is FormTransactionEvent.OnNameChanged -> changeName(event.name)
            FormTransactionEvent.OnSave -> createTransaction()
            FormTransactionEvent.OnDelete -> deleteTransaction()
            is FormTransactionEvent.ProcessSharedImage -> handleImageProcessing(event.imageBytes)
            FormTransactionEvent.HideOverlay -> hideOverlay()
            is FormTransactionEvent.OnAmountChanged -> changeAmount(event.amount)
            is FormTransactionEvent.OnTransactionDateChanged -> changeTransactionDate(event.dateTime)
        }
    }

    private fun hideOverlay() {
        _state.update { it.copy(reviewReceipt = false) }
    }

    private fun handleImageProcessing(imageBytes: ByteArray) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, reviewReceipt = true) }

            val extractedText = ocrEngine.extractTextFromImage(imageBytes)
            val parsedReceipt = parser.parse(extractedText)

            _state.update {
                it.copy(
                    isLoading = false,
                    amount = parsedReceipt.amount.toDouble(),
                    name = parsedReceipt.receiver,
                )
            }
        }
    }

    private fun deleteTransaction() {
        viewModelScope.launch {
            setLoading(true)
            dismissDialog()
            repository.deleteTransaction(state.value.transactionId)
                .onSuccess { result ->
                    _state.update { it.copy(message = result) }
                }
                .onError { error ->
                    _state.update { it.copy(isError = true, message = error.asUiText().asString()) }
                }
            setLoading(false)
            clearState()
        }
    }

    private fun showDialog() {
        _state.update { it.copy(showDeleteDialog = true) }
    }

    private fun dismissDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

    private fun showCategoriesBottomSheet() {
        _state.update { it.copy(showCategoriesBottomSheet = true) }
    }

    private fun dismissCategoriesBottomSheet() {
        _state.update { it.copy(showCategoriesBottomSheet = false) }
    }

    private fun showDatePicker() {
        _state.update { it.copy(showDatePicker = true) }
    }

    private fun dismissDatePicker() {
        _state.update { it.copy(showDatePicker = false) }
    }

    private fun showTimePicker() {
        _state.update { it.copy(showTimePicker = true) }
    }

    private fun dismissTimePicker() {
        _state.update { it.copy(showTimePicker = false) }
    }

    private suspend fun clearState() {
        delay(3.seconds)
        setLoading(false)
        if (state.value.reviewReceipt) {
            _state.update { it.copy(reviewReceipt = false) }
        }
        _state.update {
            it.copy(
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
            if (state.value.transactionId.isNotBlank()) {
                repository.getTransaction(state.value.transactionId)
                    .onSuccess { result ->
                        _state.update {
                            it.copy(
                                selectedWallet = result.wallet,
                                selectedCategory = result.category,
                                name = result.transaction.name,
                                amount = result.transaction.amount.toDouble(),
                                transactionDate = result.transaction.createdAt,
                            )
                        }
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

    fun setId(id: String) {
        _state.update { it.copy(transactionId = id) }
    }

    private fun changeWallet(wallet: Wallet) {
        _state.update { it.copy(selectedWallet = wallet) }
    }

    private fun changeCategory(category: Category, moveToFirst: Boolean) {
        _state.update { currentState ->
            val updatedCategories = if (moveToFirst) {
                val selectedItem = currentState.categories.find { it.category.id == category.id }
                if (selectedItem != null) {
                    listOf(selectedItem) + currentState.categories.filterNot { it.category.id == category.id }
                } else {
                    currentState.categories
                }
            } else {
                currentState.categories
            }
            currentState.copy(
                selectedCategory = category,
                categories = updatedCategories,
                showCategoriesBottomSheet = false,
            )
        }
    }

    private fun changeAmount(amount: Double) {
        _state.update { it.copy(amount = amount) }
    }

    private fun changeTransactionDate(dateTime: LocalDateTime) {
        _state.update { it.copy(transactionDate = dateTime) }
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
            if (state.value.amount == 0.0) {
                _state.update { it.copy(message = getString(Res.string.set_amount_error)) }
                return@launch
            }
            setLoading(true)
            if (state.value.transactionId.isBlank()) {
                repository.postTransaction(
                    userId = state.value.userId,
                    categoryId = state.value.selectedCategory?.id.toString(),
                    walletId = state.value.selectedWallet?.id.toString(),
                    amount = state.value.amount.toInt(),
                    name = state.value.name,
                    type = state.value.selectedType.name.lowercase(),
                    date = state.value.transactionDate
                        .toInstant(TimeZone.currentSystemDefault())
                        .toString()
                )
                    .onSuccess {
                        _state.update {
                            it.copy(
                                message = getString(Res.string.transaction_recorded),
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
            } else {
                repository.putTransaction(
                    transactionId = state.value.transactionId,
                    userId = state.value.userId,
                    categoryId = state.value.selectedCategory?.id.toString(),
                    walletId = state.value.selectedWallet?.id.toString(),
                    amount = state.value.amount.toInt(),
                    name = state.value.name,
                    type = state.value.selectedType.name.lowercase(),
                    date = state.value.transactionDate
                        .toInstant(TimeZone.currentSystemDefault())
                        .toString()
                )
                    .onSuccess {
                        _state.update {
                            it.copy(
                                message = getString(Res.string.transaction_recorded),
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
            }
            clearState()
        }
    }

}