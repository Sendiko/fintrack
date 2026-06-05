package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.theme.greenApproved
import id.my.sendiko.fintrack.theme.redError
import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.transaction.domain.TransactionType

@Composable
fun TransactionListItem(
    modifier: Modifier = Modifier,
    transaction: Transaction,
    categoryName: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = transaction.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = categoryName,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = transaction.amount.toDouble().toRupiah(),
            style = MaterialTheme.typography.bodyLarge,
            color = if (transaction.type == TransactionType.INCOME)
                greenApproved  else redError,
            fontWeight = FontWeight.SemiBold
        )
    }
}