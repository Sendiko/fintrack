package id.my.sendiko.fintrack.auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

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