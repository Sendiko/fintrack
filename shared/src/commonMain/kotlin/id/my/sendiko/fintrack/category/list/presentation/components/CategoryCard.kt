package id.my.sendiko.fintrack.category.list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.theme.greenApproved
import id.my.sendiko.fintrack.theme.redError
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: CategoryWithTransactions,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 8.dp,
                bottomStart = 16.dp,
                bottomEnd = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = secondaryBlue,
                contentColor = utilityWhite
            )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = category.category.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Total transactions: ${category.transactions.size}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        IconButton(
            modifier = Modifier.fillMaxHeight().aspectRatio(1f),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = greenApproved,
                contentColor = utilityWhite
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = onEdit
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )
        }
        IconButton(
            modifier = Modifier.fillMaxHeight().aspectRatio(1f),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = redError,
                contentColor = utilityWhite
            ),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 16.dp,
                bottomStart = 8.dp,
                bottomEnd = 16.dp
            ),
            onClick = onDelete
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }
    }
}