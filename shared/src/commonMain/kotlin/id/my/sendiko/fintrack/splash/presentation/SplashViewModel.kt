package id.my.sendiko.fintrack.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.splash.data.SplashRepositoryImpl
import id.my.sendiko.fintrack.splash.domain.SplashRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SplashViewModel(
    private val repository: SplashRepository
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(SplashState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashState())

}