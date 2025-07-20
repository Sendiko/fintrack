package id.my.sendiko.fintrack.core.presentation.rupiah

import kotlin.math.abs
import kotlin.math.roundToLong

fun Double.toShortRupiah(): String {
    fun formatValue(value: Double): String {
        if (value % 1.0 == 0.0) {
            return value.toLong().toString()
        } else {
            val scaledValue = (value * 10).roundToLong()
            val intPart = scaledValue / 10
            val decimalPart = scaledValue % 10
            return "$intPart.$decimalPart"
        }
    }

    val shortValue = when {
        abs(this) >= 1_000_000_000_000 -> formatValue(this / 1_000_000_000_000) + " T"
        abs(this) >= 1_000_000_000 -> formatValue(this / 1_000_000_000) + " M"
        abs(this) >= 1_000_000 -> formatValue(this / 1_000_000) + " Juta"
        abs(this) >= 1_000 -> formatValue(this / 1_000) + " Ribu"
        else -> formatValue(this)
    }

    return "Rp$shortValue"
}