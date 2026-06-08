package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.category.domain.Category
import id.my.sendiko.fintrack.theme.aliceBlue
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction

@Composable
fun TransactionsCard(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    categories: List<Category>,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = aliceBlue
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        transactions.take(4).forEach { transaction ->
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth(),
            ) {
                TransactionListItem(
                    modifier = Modifier.fillMaxWidth(),
                    transaction = transaction,
                    categoryName = categories.find { it.id == transaction.categoryId }?.name
                        ?: "Category not found."
                )
            }
        }
    }
}