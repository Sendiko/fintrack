package id.my.sendiko.fintrack.budget.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.days_left_text
import fintrack.composeapp.generated.resources.of_left
import fintrack.composeapp.generated.resources.of_limit
import id.my.sendiko.fintrack.budget.core.domain.model.Summary
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun BudgetLimitCard(
    modifier: Modifier = Modifier,
    summary: Summary
) {
    val formattedSpent = summary.spent.toDouble().toRupiah()
    val formattedLimit = summary.totalAmount.toDouble().toRupiah()

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = primaryOrange,
            contentColor = utilityWhite,
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = formattedSpent,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = utilityWhite,
                    )
                    Text(
                        text = stringResource(Res.string.of_limit, formattedLimit),
                        style = MaterialTheme.typography.bodyMedium,
                        color = utilityWhite.copy(alpha = 0.9f),
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Surface(
                        shape = CircleShape,
                        color = utilityWhite.copy(alpha = 0.25f),
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            text = "${summary.totalPercentageUsed}%",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            color = utilityWhite,
                        )
                    }

                    Text(
                        text = stringResource(Res.string.days_left_text, summary.remainingDays),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        color = utilityWhite,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(
                        color = utilityWhite.copy(alpha = 0.35f),
                        shape = CircleShape,
                    )
                    .clip(CircleShape),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 1f - (summary.totalPercentageUsed.coerceIn(0, 100) / 100f))
                        .fillMaxHeight()
                        .background(
                            color = utilityWhite,
                            shape = CircleShape,
                        ),
                )
            }
        }
    }
}

@Preview
@Composable
fun BudgetLimitCardPreview() {
    FinTrackTheme {
        BudgetLimitCard(
            modifier = Modifier.padding(16.dp),
            summary = Summary(
                totalAmount = 1000000,
                remainingAmount = 700000,
                spent = 300000,
                remainingDays = 5,
                spentToday = 100000,
                remainingToday = 50000.0,
                isActive = true,
                todayBudget = 142857.0,
                dailyPercentageUsed = 70,
                totalPercentageUsed = 30
            ),
        )
    }
}
