package id.my.sendiko.fintrack.core

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordScreen
import id.my.sendiko.fintrack.auth.changepassword.presentation.ChangePasswordViewModel
import id.my.sendiko.fintrack.auth.login.presentation.LoginScreen
import id.my.sendiko.fintrack.auth.login.presentation.LoginViewModel
import id.my.sendiko.fintrack.auth.register.presentation.RegisterScreen
import id.my.sendiko.fintrack.auth.register.presentation.RegisterViewModel
import id.my.sendiko.fintrack.core.navigation.ChangePasswordDestination
import id.my.sendiko.fintrack.core.navigation.DashboardDestination
import id.my.sendiko.fintrack.core.navigation.FormTransactionDestination
import id.my.sendiko.fintrack.core.navigation.FormWalletDestination
import id.my.sendiko.fintrack.core.navigation.ListTransactionDestination
import id.my.sendiko.fintrack.core.navigation.LoginDestination
import id.my.sendiko.fintrack.core.navigation.RegisterDestination
import id.my.sendiko.fintrack.core.navigation.SplashDestination
import id.my.sendiko.fintrack.core.navigation.WalletListDestination
import id.my.sendiko.fintrack.dashboard.presentation.DashboardScreen
import id.my.sendiko.fintrack.dashboard.presentation.DashboardViewModel
import id.my.sendiko.fintrack.splash.presentation.SplashScreen
import id.my.sendiko.fintrack.splash.presentation.SplashViewModel
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.transaction.form.presentation.FormTransactionScreen
import id.my.sendiko.fintrack.transaction.form.presentation.FormTransactionViewModel
import id.my.sendiko.fintrack.transaction.list.presentation.ListTransactionScreen
import id.my.sendiko.fintrack.transaction.list.presentation.ListTransactionViewModel
import id.my.sendiko.fintrack.wallet.form.presentation.FormWalletScreen
import id.my.sendiko.fintrack.wallet.form.presentation.FormWalletViewModel
import id.my.sendiko.fintrack.wallet.list.presentation.ListWalletScreen
import id.my.sendiko.fintrack.wallet.list.presentation.ListWalletViewModel
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
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
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
            composable<DashboardDestination> {
                val viewModel = koinViewModel<DashboardViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                DashboardScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {
                        navController.navigate(it)
                    },
                )
            }
            composable<FormWalletDestination> {
                val args = it.toRoute<FormWalletDestination>()
                val viewModel = koinViewModel<FormWalletViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                viewModel.setId(args.id)

                FormWalletScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { destination ->
                        if (destination == null) {
                            navController.navigateUp()
                        }
                    }
                )
            }
            composable<WalletListDestination> {
                val viewModel = koinViewModel<ListWalletViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                ListWalletScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { destination ->
                        if (destination == null)
                            navController.navigateUp()
                        else navController.navigate(destination)
                    }
                )
            }
            composable<FormTransactionDestination> {
                val (type, id) = it.toRoute<FormTransactionDestination>()
                val viewModel = koinViewModel<FormTransactionViewModel>()
                viewModel.setType(type)
                viewModel.setId(id)
                val state by viewModel.state.collectAsStateWithLifecycle()

                FormTransactionScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigateBack = { navController.navigateUp() }
                )
            }
            composable<ListTransactionDestination> {
                val viewModel = koinViewModel<ListTransactionViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                ListTransactionScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { destination ->
                        if (destination == null)
                            navController.navigateUp()
                        else navController.navigate(destination)
                    }
                )
            }
        }
    }
}