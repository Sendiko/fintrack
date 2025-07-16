package id.my.sendiko.fintrack.wallet.core.data.dto.get

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetWalletsResponse(

	@SerialName("wallet")
	val wallet: List<WalletsItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)