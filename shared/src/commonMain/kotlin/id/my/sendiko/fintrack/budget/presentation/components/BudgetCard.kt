package id.my.sendiko.fintrack.budget.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.rupiah.toShortRupiah
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.utilityWhite

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier,
    todayBudget: Double,
    remainingToday: Double
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = primaryOrange,
            contentColor = utilityWhite,
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = remainingToday.toRupiah(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "/" + todayBudget.toShortRupiah(),
            )
        }
    }
}

@Composable
@Preview
fun BudgetCardPrev() {
    Surface {
        BudgetCard(todayBudget = 10000.0, remainingToday = 5000.0)
    }
}