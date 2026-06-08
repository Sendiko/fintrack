package id.my.sendiko.fintrack.core.presentation

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun convertStringToDateTime(dateStr: String): LocalDateTime {
    val instant = Instant.parse(dateStr)

    return instant.toLocalDateTime(TimeZone.UTC)
}