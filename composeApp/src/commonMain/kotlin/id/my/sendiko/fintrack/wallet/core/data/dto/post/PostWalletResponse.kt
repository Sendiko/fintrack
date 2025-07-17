package id.my.sendiko.fintrack.wallet.core.data.dto.post

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostWalletResponse(

	@SerialName("wallet")
	val wallet: Wallet,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)