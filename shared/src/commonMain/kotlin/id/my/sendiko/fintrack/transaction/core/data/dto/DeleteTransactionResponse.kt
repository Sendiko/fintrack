package id.my.sendiko.fintrack.transaction.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteTransactionResponse(

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)