package id.my.sendiko.fintrack.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.all_wallets
import fintrack.composeapp.generated.resources.budget_label
import fintrack.composeapp.generated.resources.empty_budget
import fintrack.composeapp.generated.resources.empty_wallet
import fintrack.composeapp.generated.resources.recent_transaction
import fintrack.composeapp.generated.resources.see_more_budgets
import fintrack.composeapp.generated.resources.see_more_transaction
import fintrack.composeapp.generated.resources.wallets
import id.my.sendiko.fintrack.core.navigation.FormTransactionDestination
import id.my.sendiko.fintrack.core.navigation.ListBudgetDestination
import id.my.sendiko.fintrack.core.navigation.ListTransactionDestination
import id.my.sendiko.fintrack.core.navigation.ListWalletDestination
import id.my.sendiko.fintrack.core.navigation.SplashDestination
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.bodyLarge
import id.my.sendiko.fintrack.core.presentation.theme.labelMedium
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.dashboard.presentation.components.BudgetSummaryCard
import id.my.sendiko.fintrack.dashboard.presentation.components.DashboardTopBar
import id.my.sendiko.fintrack.dashboard.presentation.components.TotalNetWorthCard
import id.my.sendiko.fintrack.dashboard.presentation.components.WalletCard
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import id.my.sendiko.fintrack.transaction.presentation.components.TransactionCard
import id.my.sendiko.fintrack.wallet.core.domain.WalletType
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.seconds

@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {

    LaunchedEffect(state.message) {
        if (state.message.isNotBlank()){
            delay(1.seconds)
            onEvent(DashboardEvent.ClearState)
        }
    }

    LaunchedEffect(true) {
        onEvent(DashboardEvent.OnLoadData)
    }

    LaunchedEffect(state.logoutSuccess) {
        if (state.logoutSuccess)
            onNavigate(SplashDestination)
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading
    ) {
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { onEvent(DashboardEvent.OnLoadData) },
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.systemBarsPadding(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        DashboardTopBar(
                            modifier = Modifier.padding(top = 16.dp),
                            username = "Sendiko"
                        )
                    }
                    item {
                        TotalNetWorthCard(
                            totalNetWorth = state.wallets.sumOf { it.amount },
                            onIncomeClick = {
                                onNavigate(FormTransactionDestination(type = TransactionType.INCOME.name))
                            },
                            onExpenseClick = {
                                onNavigate(FormTransactionDestination(type = TransactionType.EXPENSE.name))
                            },
                            onTransferClick = { },
                            onAnalyticsClick = { }
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(Res.string.wallets),
                                style = bodyLarge
                            )
                            Text(
                                modifier = Modifier.clickable {
                                    onNavigate(ListWalletDestination)
                                },
                                text = stringResource(Res.string.all_wallets),
                                style = labelMedium,
                                color = primaryOrange,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                    item {
                        if (state.wallets.isNotEmpty()) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(state.wallets) { wallet ->
                                    WalletCard(
                                        walletType = WalletType.valueOf(wallet.type.uppercase()),
                                        name = wallet.name,
                                        number = wallet.number,
                                        balanceAmount = wallet.amount
                                    )

                                }
                            }
                            return@item
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.empty_wallet),
                            textAlign = TextAlign.Center
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(Res.string.budget_label),
                                style = bodyLarge
                            )
                            Text(
                                modifier = Modifier.clickable {
                                    onNavigate(ListBudgetDestination)
                                },
                                text = stringResource(Res.string.see_more_budgets),
                                style = labelMedium,
                                color = primaryOrange,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                    item {
                        if (state.summary != null) {
                            BudgetSummaryCard(summary = state.summary)
                            return@item
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.empty_budget),
                            textAlign = TextAlign.Center
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(Res.string.recent_transaction),
                                style = bodyLarge
                            )
                            Text(
                                modifier = Modifier.clickable {
                                    onNavigate(ListTransactionDestination)
                                },
                                text = stringResource(Res.string.see_more_transaction),
                                style = labelMedium,
                                color = primaryOrange,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                    if (state.transactions.isNotEmpty()) {
                        items(state.transactions.take(10)) { transaction ->
                            TransactionCard(
                                modifier = Modifier,
                                transaction = transaction
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    FinTrackTheme {
        DashboardScreen(
            state = DashboardState(),
            onEvent = { },
            onNavigate = { }
        )
    }
}