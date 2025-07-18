package id.my.sendiko.fintrack.core.presentation.numerickeyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.primaryOrange

@Composable
fun NumericKeyboard(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onBackspace: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "1",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "2",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "3",
                onClick = onClick
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "4",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "5",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "6",
                onClick = onClick
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "7",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "8",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "9",
                onClick = onClick
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackspaceButton(
                modifier = Modifier.weight(1f),
                onClick = onBackspace
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "0",
                onClick = onClick
            )
            NumericKeyboardButton(
                modifier = Modifier.weight(1f),
                value = "000",
                onClick = onClick
            )
        }
    }
}