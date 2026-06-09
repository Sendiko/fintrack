package id.my.sendiko.fintrack.transaction.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutTransactionResponse(

    @SerialName("message")
    val message: String,

    @SerialName("transaction")
    val transaction: TransactionsDto,

    @SerialName("status")
    val status: Int
)
