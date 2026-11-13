package id.my.sendiko.fintrack.budget.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.core.presentation.getColorAtPercentage
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite

@Composable
fun TodayBudgetCard(
    modifier: Modifier = Modifier,
    remainingToday: Double,
    dailyPercentageUsed: Int,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = getColorAtPercentage(dailyPercentageUsed),
            contentColor = utilityWhite,
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = remainingToday.toRupiah(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview
fun TodayBudgetCardPrev() {
    Surface {
        TodayBudgetCard(remainingToday = 5000.0, dailyPercentageUsed = 0)
    }
}