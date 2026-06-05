package id.my.sendiko.fintrack.auth.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("password")
	val password: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("email")
	val email: String,

	@SerialName("token")
	val token: String,

	@SerialName("updatedAt")
	val updatedAt: String
)