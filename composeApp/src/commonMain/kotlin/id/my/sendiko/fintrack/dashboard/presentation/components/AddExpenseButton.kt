package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.expense
import fintrack.composeapp.generated.resources.mdi_arrow_bottom
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.utilityWhite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddExpenseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = utilityWhite,
            containerColor = primaryOrange
        ),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(16.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(Res.string.expense), fontWeight = FontWeight.SemiBold)
                Icon(
                    modifier = Modifier.rotate(270f),
                    painter = painterResource(Res.drawable.mdi_arrow_bottom),
                    contentDescription = stringResource(Res.string.expense),
                    tint = utilityWhite
                )
            }
        }
    )
}

@Preview
@Composable
private fun AddExpenseButtonPreview() {
    FinTrackTheme {
        AddExpenseButton {  }
    }
}