package id.my.sendiko.fintrack.transaction.core.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostTransactionRequest(

	@SerialName("walletId")
	val walletId: String,

	@SerialName("amount")
	val amount: Int,

	@SerialName("name")
	val name: String,

	@SerialName("type")
	val type: String,

	@SerialName("userId")
	val userId: String,

	@SerialName("categoryId")
	val categoryId: String
)
