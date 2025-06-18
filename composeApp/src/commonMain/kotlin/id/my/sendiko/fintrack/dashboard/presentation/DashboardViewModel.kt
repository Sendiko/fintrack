package id.my.sendiko.fintrack.dashboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel: ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when(event) {
            DashboardEvent.ClearState -> TODO()
            is DashboardEvent.OnBalanceViewChanged -> changeBalanceVisibility(event.isVisible)
        }
    }

    private fun changeBalanceVisibility(visible: Boolean) {
        _state.update { it.copy(balanceVisible = visible) }
    }

}