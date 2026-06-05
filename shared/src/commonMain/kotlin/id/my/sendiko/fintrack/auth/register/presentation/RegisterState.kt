package id.my.sendiko.fintrack.auth.register.presentation

data class RegisterState(
    val username: String = "",
    val usernameError: String = "",
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val passwordVisible: Boolean = false,
    val termsChecked: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val message: String = ""
)