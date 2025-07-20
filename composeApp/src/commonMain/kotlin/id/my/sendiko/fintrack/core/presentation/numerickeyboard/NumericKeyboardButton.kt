package id.my.sendiko.fintrack.core.presentation.numerickeyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.aliceBlue
import id.my.sendiko.fintrack.theme.utilityBlack
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NumericKeyboardButton(
    modifier: Modifier = Modifier,
    value: String,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick(value) },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = aliceBlue,
            contentColor = utilityBlack
        ),
        content = {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    )
}

@Preview
@Composable
private fun NumericKeyButtonPreview() {
    FinTrackTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
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
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "1",
                        onClick = {}
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackspaceButton(
                        modifier = Modifier.weight(1f),
                    ) {

                    }
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = "0",
                        onClick = {}
                    )
                    NumericKeyboardButton(
                        modifier = Modifier.weight(1f),
                        value = ".",
                        onClick = {}
                    )
                }
            }
        }
    }
}