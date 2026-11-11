package id.my.sendiko.fintrack.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import id.my.sendiko.fintrack.core.presentation.theme.PlusJakartaSansTypography
import id.my.sendiko.fintrack.core.presentation.theme.colorScheme

@Composable
actual fun FinTrackTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = PlusJakartaSansTypography()
    ) {
        content()
    }
}