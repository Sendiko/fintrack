package id.my.sendiko.fintrack.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.auth.login.domain.LoginRepository
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnUsernameChanged -> changeUsername(event.username)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            LoginEvent.OnLogin -> login()
        }
    }

    private suspend fun clearState() {
        delay(2.seconds)
        _state.update {
            it.copy(
                usernameError = "",
                passwordError = "",
                isLoading = false,
                isSuccess = false,
                isError = false,
                message = ""
            )
        }
    }

    fun changeUsername(username: String) {
        _state.update {
            it.copy(username = username)
        }
    }

    fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun changePasswordVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(isPasswordVisible = isVisible)
        }
    }

    fun login() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.login(
                username = state.value.username,
                password = state.value.password
            ).onSuccess { result ->
                repository.saveToken(result.token)
                repository.saveUserId(result.data.userId)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        message = "Welcome, ${result.data.name}!"
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        message = error.asUiText().asString()
                    )
                }
            }
            clearState()
        }
    }

}