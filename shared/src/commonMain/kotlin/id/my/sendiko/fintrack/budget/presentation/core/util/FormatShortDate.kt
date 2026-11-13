package id.my.sendiko.fintrack.budget.presentation.core.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

fun String.formatDate(): String {
    val parsedDate = LocalDate.parse(this)
    val formatter = LocalDate.Format {
        this@Format.day(padding = Padding.ZERO)
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        yearTwoDigits(baseYear = 2000)
    }
    return parsedDate.format(formatter)
}