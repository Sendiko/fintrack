package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.dashboard.presentation.toRupiah
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.theme.variantBrown

@Composable
fun TopCategoryCard(
    modifier: Modifier = Modifier,
    category: String,
    amount: Double
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = variantBrown,
            contentColor = utilityWhite
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = amount.toRupiah(),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}