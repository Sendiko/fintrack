package id.my.sendiko.fintrack.core.presentation.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.core.presentation.theme.labelLarge
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryOrange,
            contentColor = utilityWhite
        ),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 32.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            style = labelLarge
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPrev() {
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { },
        text = "Login"
    )
}