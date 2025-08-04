package id.my.sendiko.fintrack.wallet.core.data.dto.getdetails

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetWalletsResponse(

	@SerialName("wallets")
	val wallets: List<WalletsItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)