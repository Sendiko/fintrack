package id.my.sendiko.fintrack.transaction.form.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType

@Composable
fun TransactionTypeRadioButton(
    modifier: Modifier = Modifier,
    type: TransactionType,
    onClick: () -> Unit,
    isSelected: Boolean,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (isSelected) primaryOrange.copy(alpha = 0.2f)
            else utilityWhite,
            contentColor = if (isSelected) primaryOrange else secondaryBlue
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) primaryOrange else secondaryBlue
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = primaryOrange,
                    unselectedColor = secondaryBlue
                )
            )
            Text(
                text = type.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}