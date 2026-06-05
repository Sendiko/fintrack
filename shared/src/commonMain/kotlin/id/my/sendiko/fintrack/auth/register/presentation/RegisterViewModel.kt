package id.my.sendiko.fintrack.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.auth.register.data.dto.RegisterRequest
import id.my.sendiko.fintrack.auth.register.domain.RegisterRepository
import id.my.sendiko.fintrack.core.network.utils.DataError.Remote.*
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import id.my.sendiko.fintrack.core.presentation.errorToUiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class RegisterViewModel(
    val repository: RegisterRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnUsernameChanged -> changeUsername(event.username)
            is RegisterEvent.OnEmailChanged -> changeEmail(event.email)
            is RegisterEvent.OnPasswordChanged -> changePassword(event.password)
            is RegisterEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            is RegisterEvent.OnTermsCheckChanged -> changeTermsCheck(event.isChecked)
            RegisterEvent.OnRegisterClicked -> register()
        }
    }

    private suspend fun clearState() {
        delay(2.seconds)
        _state.update { it.copy(
            usernameError = "",
            passwordError = "",
            isLoading = false,
            isSuccess = false,
            isError = false,
            message = ""
        ) }
    }

    private fun register() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.register(
                name = state.value.username,
                email = state.value.email,
                password = state.value.password
            )
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            message = "Hello, ${result.name}!"
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            message = when (error) {
                                BAD_REQUEST -> "Email is already registered."
                                else -> errorToUiText(error)
                            }
                        )
                    }
                }
            clearState()
        }
    }

    private fun changeTermsCheck(isChecked: Boolean) {
        _state.update { it.copy(termsChecked = isChecked) }
    }

    private fun changePasswordVisibility(isVisible: Boolean) {
        _state.update { it.copy(passwordVisible = isVisible) }
    }

    private fun changePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun changeUsername(username: String) {
        _state.update { it.copy(username = username) }
    }

    private fun changeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

}