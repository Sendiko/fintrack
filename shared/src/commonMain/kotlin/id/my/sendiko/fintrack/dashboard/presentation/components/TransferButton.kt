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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.expense
import fintrack.composeapp.generated.resources.mdi_arrow_bottom
import fintrack.composeapp.generated.resources.transfer
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransferButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = utilityWhite,
            containerColor = secondaryBlue
        ),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(16.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(Res.string.transfer), fontWeight = FontWeight.SemiBold)
                Icon(
                    modifier = Modifier.rotate(315f),
                    painter = painterResource(Res.drawable.mdi_arrow_bottom),
                    contentDescription = stringResource(Res.string.transfer),
                    tint = utilityWhite
                )
            }
        }
    )
}

@Preview
@Composable
fun TransferButtonPrev() {
    FinTrackTheme {
        TransferButton(
            onClick = { }
        )
    }
}