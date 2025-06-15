package id.my.sendiko.fintrack.auth.changepassword.presentation

sealed interface ChangePasswordEvent {
    data class OnEmailChanged(val email: String): ChangePasswordEvent
    data class OnPasswordChanged(val password: String): ChangePasswordEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): ChangePasswordEvent
    data object ClearState: ChangePasswordEvent
    data object OnVerifyEmail: ChangePasswordEvent
    data object OnChangePassword: ChangePasswordEvent
}