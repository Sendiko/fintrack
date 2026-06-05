package id.my.sendiko.fintrack.core.network.utils

import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.*
import id.my.sendiko.fintrack.core.presentation.UiText
import id.my.sendiko.fintrack.core.presentation.UiText.CmpStringResource

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Remote.RequestTimeout -> CmpStringResource(
            Res.string.the_request_timed_out
        )

        DataError.Remote.TooManyRequests -> CmpStringResource(
            Res.string.youve_hit_your_rate_limit
        )

        DataError.Remote.NoInternet -> CmpStringResource(
            Res.string.no_internet
        )

        DataError.Remote.Serialization -> CmpStringResource(
            Res.string.error_serialization
        )

        DataError.Remote.Unknown -> CmpStringResource(
            Res.string.unknown_error
        )

        DataError.Local.UNKNOWN -> CmpStringResource(
            Res.string.unknown_error
        )

        DataError.Remote.ServerError -> CmpStringResource(
            Res.string.server_error
        )

        DataError.Remote.NotFound -> CmpStringResource(
            Res.string.not_found
        )

        DataError.Remote.BadRequest -> CmpStringResource(
            Res.string.bad_request
        )

        DataError.Remote.Unauthorized -> CmpStringResource(
            Res.string.unauthorized
        )

        DataError.Local.DISK_FULL -> CmpStringResource(
            Res.string.error_disk_full
        )

        DataError.Remote.Conflict -> CmpStringResource(
            Res.string.conflict
        )

        DataError.Remote.Forbidden -> CmpStringResource(
            Res.string.forbidden
        )

        is DataError.Remote.ApiErrorMessage -> UiText.DynamicString(message)
    }
}