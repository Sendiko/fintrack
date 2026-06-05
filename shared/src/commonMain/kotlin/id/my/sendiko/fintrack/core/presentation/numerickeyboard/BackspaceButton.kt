package id.my.sendiko.fintrack.core.presentation.numerickeyboard

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.backspace
import id.my.sendiko.fintrack.theme.aliceBlue
import id.my.sendiko.fintrack.theme.utilityBlack
import org.jetbrains.compose.resources.stringResource

@Composable
fun BackspaceButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = aliceBlue,
            contentColor = utilityBlack
        ),
        content = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Backspace,
                contentDescription = stringResource(Res.string.backspace)
            )
        }
    )
}