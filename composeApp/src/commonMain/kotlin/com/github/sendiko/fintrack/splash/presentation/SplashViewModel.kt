package com.github.sendiko.fintrack.splash.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sendiko.fintrack.splash.data.SplashRepositoryImpl
import kotlinx.coroutines.flow.*

class SplashViewModel(
    repository: SplashRepositoryImpl
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(SplashState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashState())

}