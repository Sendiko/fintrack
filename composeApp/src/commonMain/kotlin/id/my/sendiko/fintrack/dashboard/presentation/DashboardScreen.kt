package id.my.sendiko.fintrack.dashboard.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.recet_transaction
import fintrack.composeapp.generated.resources.settings
import fintrack.composeapp.generated.resources.top_expense_label
import fintrack.composeapp.generated.resources.total_balance
import fintrack.composeapp.generated.resources.wallets
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.dashboard.data.categories
import id.my.sendiko.fintrack.dashboard.data.transactions
import id.my.sendiko.fintrack.dashboard.presentation.components.AddExpenseButton
import id.my.sendiko.fintrack.dashboard.presentation.components.AddIncomeButton
import id.my.sendiko.fintrack.dashboard.presentation.components.AddWalletButton
import id.my.sendiko.fintrack.dashboard.presentation.components.TopCategoryCard
import id.my.sendiko.fintrack.dashboard.presentation.components.TransactionListItem
import id.my.sendiko.fintrack.dashboard.presentation.components.WalletCard
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.aliceBlue
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {

    LaunchedEffect(state.token) {
        if (state.token.isNotBlank()) {
            onEvent(DashboardEvent.OnLoadData)
        }
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
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(Res.string.total_balance),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = state.wallets.sumOf { it.amount }.toRupiah(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = stringResource(Res.string.settings)
                            )
                        }
                    }
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
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            AddWalletButton(
                                modifier = Modifier.width(48.dp)
                                    .height(128.dp),
                                onClick = { onNavigate(Any()) }
                            )
                        }
                        items(state.wallets) { wallet ->
                            WalletCard(
                                wallet = wallet,
                                isVisible = state.balanceVisible,
                                onVisibilityToggle = {
                                    onEvent(DashboardEvent.OnBalanceViewChanged(it))
                                }
                            )
                        }
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
                            onClick = { onNavigate(Any()) }
                        )
                        AddExpenseButton(
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate(Any()) }
                        )
                    }
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = stringResource(Res.string.top_expense_label),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    AnimatedVisibility(
                        visible = state.topCategory.isNotEmpty(),
                        enter = slideInHorizontally(),
                        exit = slideOutHorizontally()
                    ) {
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
                    }
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = stringResource(Res.string.recet_transaction),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = aliceBlue
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        state.transactions.take(4).forEach { transaction ->
                            Column(
                                modifier = Modifier
                                    .padding(all = 16.dp)
                                    .fillMaxWidth(),
                            ) {
                                TransactionListItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    transaction = transaction,
                                    categoryName = state.categories.find { it.id == transaction.categoryId }?.name ?: "Category not found."
                                )
                            }
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
            onEvent = {  },
            onNavigate = {  }
        )
    }
}