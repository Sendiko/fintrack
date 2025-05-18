package com.github.sendiko.fintrack.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val colorScheme = lightColorScheme(
    primary = primaryOrange,
    primaryContainer = variantBrown,
    onPrimaryContainer = utilityWhite,
    secondary = secondaryBlue,
    secondaryContainer = aliceBlue,
    onSecondaryContainer = secondaryBlue,
    surface = utilityWhite,
    surfaceVariant = aliceBlue,
    onSurface = utilityBlack,
    onSurfaceVariant = secondaryBlue
)

@Composable
expect fun FinTrackTheme(
    content: @Composable () -> Unit,
)

