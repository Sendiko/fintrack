package id.my.sendiko.fintrack.dashboard.presentation

sealed interface DashboardEvent {
    data object OnLoadData: DashboardEvent
    data object ClearState: DashboardEvent
    data object OnLogout: DashboardEvent
    data class OnBalanceViewChanged(val isVisible: Boolean): DashboardEvent
}