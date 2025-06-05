package id.my.sendiko.fintrack.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.auth.register.data.RegisterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    private fun register() {
        print("RegisterViewModel, register clicked.")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            print("RegisterViewModel, register: ${state.value.isLoading}")
            delay(10000)
            _state.update { it.copy(isLoading = false) }
            print("RegisterViewModel, register: ${state.value.isLoading}")
//            val request = RegisterRequest(
//                name = state.value.username,
//                email = state.value.email,
//                password = state.value.password
//            )
//            repository.register(request)
//                .onSuccess { result ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                            isSuccess = true,
//                            message = "Hello, ${result.userData.name}!"
//                        )
//                    }
//                }
//                .onError { error ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                            isError = true,
//                            message = errorToUiText(error)
//                        )
//                    }
//                }
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