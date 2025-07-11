package id.my.sendiko.fintrack.wallet.core.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetWalletsResponse(

	@SerialName("wallet")
	val wallet: List<WalletItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)