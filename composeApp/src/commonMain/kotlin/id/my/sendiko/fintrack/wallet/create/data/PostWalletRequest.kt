package id.my.sendiko.fintrack.wallet.create.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostWalletRequest(

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
