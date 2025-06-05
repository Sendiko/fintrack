package id.my.sendiko.fintrack.auth.register.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RegisterResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val userData: UserData,

	@SerialName("status")
	val status: Int
)

@Serializable
data class UserData(

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

	@SerialName("updatedAt")
	val updatedAt: String
)
