package id.my.sendiko.fintrack.core.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.redError

/**
 * Returns a color along the gradient between redError and greenApproved.
 * @param percentage Value from 0 to 100
 */
fun getColorAtPercentage(percentage: Int): Color {
    val fraction = (percentage.coerceIn(0, 100) / 100f)
    return lerp(redError, greenApproved, fraction)
}