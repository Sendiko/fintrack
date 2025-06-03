package com.github.sendiko.fintrack.auth.login

data class LoginState(
    val username: String = "",
    val usernameError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val isPasswordVisible: Boolean = false,
)
