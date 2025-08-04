package id.my.sendiko.fintrack.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.category.domain.TopCategory
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.errorToUiText
import id.my.sendiko.fintrack.dashboard.data.DashboardRepository
import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.transaction.domain.TransactionType
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
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
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(DashboardState())
    val state = combine(_state, _token, _userId) { state, token, userId ->
        state.copy(token = token, userId = userId)
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
                    val categories = result.category.map { category ->
                        Category(
                            id = category.id,
                            name = category.name
                        )
                    }
                    val topCategory = result.category
                        .map { categoryItem ->
                            val totalAmount = categoryItem.transactions.sumOf { it.amount }
                            categoryItem to totalAmount
                        }
                        .sortedByDescending { it.second }
                        .take(2)
                        .map { (categoryItem, totalAmount) ->
                            TopCategory(
                                name = categoryItem.name,
                                totalAmount = totalAmount.toDouble()
                            )
                        }

                    println("Top Category: $topCategory")

                    _state.update {
                        it.copy(
                            categories = categories,
                            isLoading = false,
                            topCategory = topCategory
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
            repository.getTransactions(state.value.token)
                .onSuccess { result ->
                    val transactions = result.transaction
                    transactions.map { it ->
                        val new = Transaction(
                            id = it.id,
                            name = it.name,
                            amount = it.amount.toFloat(),
                            type = TransactionType.valueOf(it.type.uppercase()),
                            categoryId = it.categoryId,
                            userId = it.userId,
                            walletId = it.walletId
                        )
                        _state.update { it.copy(transactions = it.transactions + new) }
                    }
                    transactions.groupBy { item -> item.categoryId }
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