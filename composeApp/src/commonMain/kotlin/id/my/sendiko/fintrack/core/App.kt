package id.my.sendiko.fintrack.core

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordScreen
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordViewModel
import id.my.sendiko.fintrack.auth.login.presentation.LoginScreen
import id.my.sendiko.fintrack.auth.login.presentation.LoginViewModel
import id.my.sendiko.fintrack.auth.register.presentation.RegisterScreen
import id.my.sendiko.fintrack.auth.register.presentation.RegisterViewModel
import id.my.sendiko.fintrack.core.navigation.ChangePasswordDestination
import id.my.sendiko.fintrack.core.navigation.DashboardDestination
import id.my.sendiko.fintrack.core.navigation.LoginDestination
import id.my.sendiko.fintrack.core.navigation.RegisterDestination
import id.my.sendiko.fintrack.core.navigation.SplashDestination
import id.my.sendiko.fintrack.dashboard.presentation.DashboardScreen
import id.my.sendiko.fintrack.dashboard.presentation.DashboardViewModel
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
            startDestination = SplashDestination,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
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
                val viewModel = koinViewModel<RegisterViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                RegisterScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { navController.navigate(it) }
                )
            }
            composable<LoginDestination> {
                val viewModel = koinViewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { navController.navigate(it) }
                )
            }
            composable<ChangePasswordDestination> {
                val viewModel = koinViewModel<ChangePasswordViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                ChangePasswordScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { navController.navigate(it) }
                )
            }
            composable<DashboardDestination>{
                val viewModel = viewModel<DashboardViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                DashboardScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {  },
                )
            }
        }
    }
}