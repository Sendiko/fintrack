package id.my.sendiko.fintrack.core.presentation

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.DataError.Remote.*

fun errorToUiText(error: DataError.Remote): String {
    return when (error) {
        REQUEST_TIMEOUT -> "Connection trouble, try again later."
        TOO_MANY_REQUESTS -> "Too many request, try again later."
        NO_INTERNET -> "Can't connect to the internet."
        SERVER -> "Server error, try again later."
        SERIALIZATION -> "Serialization error, please contact the developer"
        UNKNOWN -> "Unknown error."
        NOT_FOUND -> "Can't find what you looking for."
        BAD_REQUEST -> "Bad request, please check your input."
    }
}