package id.my.sendiko.fintrack.core.presentation

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.DataError.Remote.*

fun errorToUiText(error: DataError.Remote): String {
    return when (error) {
        else -> ""
    }
}