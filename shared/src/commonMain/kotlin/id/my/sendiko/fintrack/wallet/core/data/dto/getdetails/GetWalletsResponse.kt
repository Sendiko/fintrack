package id.my.sendiko.fintrack.wallet.core.data.dto.getdetails

import id.my.sendiko.fintrack.wallet.core.data.dto.WalletDtoWithTransaction
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetWalletsResponse(

    @SerialName("wallets")
	val wallets: List<WalletDtoWithTransaction>,

    @SerialName("message")
	val message: String,

    @SerialName("status")
	val status: Int
)