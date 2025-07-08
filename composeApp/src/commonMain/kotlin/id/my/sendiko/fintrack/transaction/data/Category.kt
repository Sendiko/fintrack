package id.my.sendiko.fintrack.transaction.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Category(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("userId")
	val userId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)