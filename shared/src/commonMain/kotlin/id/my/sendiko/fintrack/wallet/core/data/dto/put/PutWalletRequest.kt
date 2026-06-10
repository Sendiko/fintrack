package id.my.sendiko.fintrack.wallet.core.data.dto.put

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutWalletRequest(

    @SerialName("balance")
    val balance: Double,

    @SerialName("purpose")
    val purpose: String,

    @SerialName("name")
    val name: String,

    @SerialName("type")
    val type: String,

    @SerialName("userId")
    val userId: String
)