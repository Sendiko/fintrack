package id.my.sendiko.fintrack.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.empty_category
import fintrack.composeapp.generated.resources.empty_transaction
import fintrack.composeapp.generated.resources.empty_wallet
import fintrack.composeapp.generated.resources.recent_transaction
import fintrack.composeapp.generated.resources.see_more_categories
import fintrack.composeapp.generated.resources.top_category
import fintrack.composeapp.generated.resources.wallets
import id.my.sendiko.fintrack.core.navigation.FormTransactionDestination
import id.my.sendiko.fintrack.core.navigation.FormWalletDestination
import id.my.sendiko.fintrack.core.navigation.ListCategoryDestination
import id.my.sendiko.fintrack.core.navigation.ListTransactionDestination
import id.my.sendiko.fintrack.core.navigation.ListWalletDestination
import id.my.sendiko.fintrack.core.navigation.SplashDestination
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.dashboard.presentation.components.AddExpenseButton
import id.my.sendiko.fintrack.dashboard.presentation.components.AddIncomeButton
import id.my.sendiko.fintrack.dashboard.presentation.components.AddWalletButton
import id.my.sendiko.fintrack.dashboard.presentation.components.AllWalletButton
import id.my.sendiko.fintrack.dashboard.presentation.components.DashboardTopBar
import id.my.sendiko.fintrack.dashboard.presentation.components.TopCategoryCard
import id.my.sendiko.fintrack.dashboard.presentation.components.TransactionsCard
import id.my.sendiko.fintrack.dashboard.presentation.components.WalletCard
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import org.jetbrains.compose.resources.stringResource

@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {

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
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.systemBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DashboardTopBar(
                        balance = state.wallets.sumOf { it.amount },
                        onLogout = { onEvent(DashboardEvent.OnLogout) }
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = stringResource(Res.string.wallets),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(horizontal = 16.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AddWalletButton(
                            modifier = Modifier.width(48.dp)
                                .fillMaxHeight(),
                            onClick = { onNavigate(FormWalletDestination()) }
                        )
                        if (state.wallets.isNotEmpty()) {
                            state.wallets.forEach { wallet ->
                                WalletCard(
                                    wallet = wallet,
                                    isVisible = state.balanceVisible,
                                    onVisibilityToggle = {
                                        onEvent(DashboardEvent.OnBalanceViewChanged(it))
                                    }
                                )
                            }
                        } else {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = stringResource(Res.string.empty_wallet),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        AllWalletButton(
                            modifier = Modifier.width(48.dp)
                                .fillMaxHeight(),
                            onClick = {
                                onNavigate(ListWalletDestination)
                            }
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AddIncomeButton(
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate(FormTransactionDestination(TransactionType.INCOME.name)) }
                        )
                        AddExpenseButton(
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate(FormTransactionDestination(TransactionType.EXPENSE.name)) }
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(Res.string.top_category),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            modifier = Modifier.clickable { onNavigate(ListCategoryDestination) },
                            text = stringResource(Res.string.see_more_categories),
                            style = MaterialTheme.typography.labelLarge,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                item {
                    if (state.categories.isNotEmpty()) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            TopCategoryCard(
                                modifier = Modifier.weight(1f),
                                category = state.topCategory.first().name,
                                amount = state.topCategory.first().totalAmount
                            )
                            TopCategoryCard(
                                modifier = Modifier.weight(1f),
                                category = state.topCategory.last().name,
                                amount = state.topCategory.last().totalAmount
                            )
                        }
                        return@item
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.empty_category),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = stringResource(Res.string.recent_transaction),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    if (state.transactions.isNotEmpty()) {
                        TransactionsCard(
                            transactions = state.transactions,
                            onSeeMoreClick = { onNavigate(ListTransactionDestination) }
                        )
                        return@item
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.empty_transaction),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
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