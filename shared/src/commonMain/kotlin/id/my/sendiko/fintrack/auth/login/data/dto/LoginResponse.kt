package id.my.sendiko.fintrack.auth.login.data.dto

import id.my.sendiko.fintrack.auth.core.data.UserItem
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val userItem: UserItem,

	@SerialName("status")
	val status: Int
)