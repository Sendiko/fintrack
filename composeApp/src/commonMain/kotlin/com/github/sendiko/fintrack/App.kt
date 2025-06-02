package com.github.sendiko.fintrack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sendiko.fintrack.auth.register.RegisterScreen
import com.github.sendiko.fintrack.auth.register.RegisterViewModel
import com.github.sendiko.fintrack.core.navigation.RegisterDestination
import com.github.sendiko.fintrack.core.navigation.SplashDestination
import com.github.sendiko.fintrack.splash.presentation.SplashScreen
import com.github.sendiko.fintrack.splash.presentation.SplashViewModel
import com.github.sendiko.fintrack.theme.FinTrackTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    FinTrackTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = SplashDestination
        ) {
            composable<SplashDestination> {
                val viewModel = koinViewModel<SplashViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                SplashScreen(
                    state = state,
                    onNavigate = {
                        navController.navigate(it)
                    }
                )
            }
            composable<RegisterDestination> {
                val viewModel = viewModel<RegisterViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                RegisterScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {  }
                )
            }
        }
    }
}