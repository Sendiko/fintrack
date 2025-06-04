package id.my.sendiko.fintrack.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnUsernameChanged -> changeUsername(event.username)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
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

    }

}