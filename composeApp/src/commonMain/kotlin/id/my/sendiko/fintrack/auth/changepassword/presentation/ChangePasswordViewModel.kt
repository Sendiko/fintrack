package id.my.sendiko.fintrack.auth.changepassword.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChangePasswordViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChangePasswordState())
    val state = _state.asStateFlow()

    fun onEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.OnEmailChanged -> changeEmail(event.email)
            ChangePasswordEvent.onVerifyEmail -> verifyEmail()
        }
    }

    private fun changeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun verifyEmail() {

    }

}