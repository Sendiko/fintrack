package com.github.sendiko.fintrack.auth.register

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
    val isError: Boolean = false
)