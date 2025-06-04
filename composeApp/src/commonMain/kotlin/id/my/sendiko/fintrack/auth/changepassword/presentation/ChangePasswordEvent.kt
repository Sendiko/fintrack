package id.my.sendiko.fintrack.auth.changepassword.presentation

sealed interface ChangePasswordEvent {
    data class OnEmailChanged(val email: String): ChangePasswordEvent
    data object onVerifyEmail: ChangePasswordEvent
}