package id.my.sendiko.fintrack.core.network.utils

sealed interface DataError : Error {
    sealed interface Remote : DataError {
        data object RequestTimeout: Remote
        data object TooManyRequests: Remote
        data object NoInternet: Remote
        data object ServerError: Remote
        data object Serialization: Remote
        data object Unknown: Remote
        data object NotFound: Remote
        data object BadRequest: Remote
        data object Unauthorized: Remote
        data object Forbidden: Remote
        data object Conflict: Remote

        data class ApiErrorMessage(val message: String) : Remote
    }

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}