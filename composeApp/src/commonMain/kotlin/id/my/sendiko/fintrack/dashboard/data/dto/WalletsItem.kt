package id.my.sendiko.fintrack.dashboard.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WalletsItem(

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