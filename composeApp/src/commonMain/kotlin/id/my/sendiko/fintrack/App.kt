package id.my.sendiko.fintrack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.my.sendiko.fintrack.auth.login.LoginScreen
import id.my.sendiko.fintrack.auth.login.LoginViewModel
import id.my.sendiko.fintrack.auth.register.RegisterScreen
import id.my.sendiko.fintrack.auth.register.RegisterViewModel
import id.my.sendiko.fintrack.core.navigation.LoginDestination
import id.my.sendiko.fintrack.core.navigation.RegisterDestination
import id.my.sendiko.fintrack.core.navigation.SplashDestination
import id.my.sendiko.fintrack.splash.presentation.SplashScreen
import id.my.sendiko.fintrack.splash.presentation.SplashViewModel
import id.my.sendiko.fintrack.theme.FinTrackTheme
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
            composable<LoginDestination> {
                val viewModel = viewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {  }
                )
            }
        }
    }
}