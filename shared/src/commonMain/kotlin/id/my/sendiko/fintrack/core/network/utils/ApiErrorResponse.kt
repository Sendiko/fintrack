package id.my.sendiko.fintrack.core.network.utils

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val status: Int,
    val message: String
)
