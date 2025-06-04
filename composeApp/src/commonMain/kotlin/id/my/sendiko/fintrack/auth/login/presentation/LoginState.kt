package id.my.sendiko.fintrack.auth.login.presentation

data class LoginState(
    val username: String = "",
    val usernameError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val isPasswordVisible: Boolean = false,
)
