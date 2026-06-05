package id.my.sendiko.fintrack.auth.login.presentation

sealed interface LoginEvent {
    data class OnUsernameChanged(val username: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean) : LoginEvent
    data object OnLogin: LoginEvent
}