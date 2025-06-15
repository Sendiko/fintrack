package id.my.sendiko.fintrack.auth.changepassword.presentation

data class ChangePasswordState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val userId: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String = ""
)
