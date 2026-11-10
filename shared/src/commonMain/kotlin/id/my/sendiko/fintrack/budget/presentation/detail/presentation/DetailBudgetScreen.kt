package id.my.sendiko.fintrack.budget.presentation.detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.budget_label
import fintrack.composeapp.generated.resources.for_day
import id.my.sendiko.fintrack.budget.presentation.core.util.formatDate
import id.my.sendiko.fintrack.budget.presentation.detail.presentation.components.BudgetStatusChip
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailBudgetScreen(
    state: DetailBudgetState,
    onEvent: (DetailBudgetEvent) -> Unit,
    onNavigateBack: () -> Unit
) {

    NotificationBox(
        message = state.message.asComposableString(),
        isLoading = state.isLoading,
    ) {
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = { onEvent(DetailBudgetEvent.OnRefresh) },
        ) {
            Scaffold(
                containerColor = utilityWhite,
                topBar = {
                    MediumTopAppBar(
                        title = {
                            Text(stringResource(Res.string.budget_label))
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onNavigateBack
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = stringResource(Res.string.back)
                                )
                            }
                        }
                    )
                }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = it.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = state.budget?.amount?.toDouble()?.toRupiah() ?: "Rp.000,00",
                                style = MaterialTheme.typography.headlineMedium
                            )
                            BudgetStatusChip(isActive = state.budget?.summary?.isActive ?: false)
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(
                                Res.string.for_day,
                                state.budget?.startDate?.formatDate() ?: "",
                                state.budget?.endDate?.formatDate() ?: ""
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun DetailBudgetScreenPrev() {
    FinTrackTheme {
        DetailBudgetScreen(
            state = DetailBudgetState(isLoading = false),
            onEvent = {  },
            onNavigateBack = {  }
        )
    }
}