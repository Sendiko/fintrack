package id.my.sendiko.fintrack.auth.register

sealed interface RegisterEvent {
    data class OnUsernameChanged(val username: String) : RegisterEvent
    data class OnEmailChanged(val email: String) : RegisterEvent
    data class OnPasswordChanged(val password: String) : RegisterEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean) : RegisterEvent
    data class OnTermsCheckChanged(val isChecked: Boolean) : RegisterEvent
    data object OnRegisterClicked : RegisterEvent
}