package id.my.sendiko.fintrack.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.auth.login.data.LoginRepository
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.errorToUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnUsernameChanged -> changeUsername(event.username)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            LoginEvent.OnLogin -> login()
        }
    }

    fun changeUsername(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    fun changePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun changePasswordVisibility(isVisible: Boolean) {
        _state.value = _state.value.copy(isPasswordVisible = isVisible)
    }

    fun login() {
        _state.update { it.copy(isLoading = true)}
        viewModelScope.launch {
            repository.login(
                username = state.value.username,
                password = state.value.password
            ).onSuccess { result ->
                repository.saveToken(result.userItem.token)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        message = "Welcome, ${result.userItem.name}!"
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        message = errorToUiText(error)
                    )
                }
            }
        }
    }

}