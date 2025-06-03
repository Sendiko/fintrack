package com.github.sendiko.fintrack.auth.login

sealed interface LoginEvent {
    data class OnUsernameChanged(val username: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean) : LoginEvent
}