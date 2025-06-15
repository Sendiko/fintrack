package id.my.sendiko.fintrack.auth.changepassword.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SearchUserResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val user: User,

	@SerialName("status")
	val status: Int
)

@Serializable
data class User(

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("email")
	val email: String
)
