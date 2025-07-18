package id.my.sendiko.fintrack.transaction.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Wallet(

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

	@SerialName("walletNumber")
	val walletNumber: String?,

	@SerialName("updatedAt")
	val updatedAt: String
)