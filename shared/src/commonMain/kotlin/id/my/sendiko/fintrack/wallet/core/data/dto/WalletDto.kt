package id.my.sendiko.fintrack.wallet.core.data.dto

import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WalletDto(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("balance")
    val balance: Int,

    @SerialName("purpose")
    val purpose: String,

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: String,

    @SerialName("type")
    val type: String,

    @SerialName("userId")
    val userId: String,

    @SerialName("updatedAt")
    val updatedAt: String
) {

    fun toDomain() = Wallet(
        id = id,
        name = name,
        purpose = purpose,
        type = type,
        amount = balance.toDouble(),
        number = ""
    )
}