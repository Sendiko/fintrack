package id.my.sendiko.fintrack.budget.presentation.list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.for_day
import id.my.sendiko.fintrack.budget.domain.model.Budget
import id.my.sendiko.fintrack.budget.domain.model.Summary
import id.my.sendiko.fintrack.budget.presentation.core.util.formatDate
import id.my.sendiko.fintrack.budget.presentation.detail.presentation.components.BudgetStatusChip
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier,
    budget: Budget
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = budget.amount.toDouble().toRupiah(),
                style = MaterialTheme.typography.headlineMedium
            )
            BudgetStatusChip(isActive = budget.summary.isActive)
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                Res.string.for_day,
                budget.startDate.formatDate(),
                budget.endDate.formatDate()
            ),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetCardPrev() {
    FinTrackTheme {
        BudgetCard(
            modifier = Modifier.padding(16.dp),
            budget = Budget(
                id = "",
                amount = 650000,
                startDate = "2026-10-01",
                endDate = "2026-10-30",
                summary = Summary(
                    totalAmount = 0,
                    remainingAmount = 0,
                    spent = 0,
                    remainingDays = 0,
                    spentToday = 0,
                    remainingToday = 0.0,
                    isActive = false,
                    todayBudget = 0.0
                )
            )
        )
    }
}