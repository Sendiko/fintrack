package id.my.sendiko.fintrack.core.presentation.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.core.presentation.theme.surface

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String? = ""
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = iconButtonColors(
            containerColor = surface,
            contentColor = id.my.sendiko.fintrack.core.presentation.theme.icon
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonPrev() {
    IconButton(
        modifier = Modifier,
        icon = Icons.Default.Add,
        onClick = {  }
    )
}