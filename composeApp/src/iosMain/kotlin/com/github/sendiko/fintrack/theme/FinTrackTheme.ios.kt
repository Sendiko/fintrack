package com.github.sendiko.fintrack.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
actual fun FinTrackTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = PlusJakartaSansTypography()
    ) {
        content()
    }
}