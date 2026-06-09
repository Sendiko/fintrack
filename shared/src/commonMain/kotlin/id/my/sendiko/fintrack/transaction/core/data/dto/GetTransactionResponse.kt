package id.my.sendiko.fintrack.transaction.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTransactionResponse(

    @SerialName("message")
    val message: String,

    @SerialName("transaction")
    val transaction: TransactionsDtoWithWallet,

    @SerialName("status")
    val status: Int
)
