package id.my.sendiko.fintrack.auth.changepassword.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.auth.changepassword.data.ChangePasswordRepositoryImpl
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val repository: ChangePasswordRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(ChangePasswordState())
    val state = _state.asStateFlow()

    fun onEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.OnEmailChanged -> changeEmail(event.email)
            ChangePasswordEvent.OnVerifyEmail -> verifyEmail()
            ChangePasswordEvent.OnChangePassword -> updatePassword()
            is ChangePasswordEvent.OnPasswordChanged -> changePassword(event.password)
            is ChangePasswordEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            ChangePasswordEvent.ClearState -> clearState()
        }
    }

    private fun clearState() {
        _state.update { it.copy(isLoading = false, isError = false, message = "") }
    }

    private fun changePasswordVisibility(visible: Boolean) {
        _state.update { it.copy(isPasswordVisible = visible) }
    }

    private fun updatePassword() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.updatePassword(_state.value.userId, _state.value.password)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            message = result
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            message = error.asUiText().asString()
                        )
                    }
                }
        }
    }

    private fun changePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun changeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun verifyEmail() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.searchUser(_state.value.email)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            userId = result.userId,
                            isLoading = false,
                            message = "Account verified."
                        )
                    }
                }
        }
    }

}