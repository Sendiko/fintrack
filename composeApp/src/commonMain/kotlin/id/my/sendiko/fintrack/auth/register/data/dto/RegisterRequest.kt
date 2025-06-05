package id.my.sendiko.fintrack.auth.register.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(

	@SerialName("password")
	val password: String,

	@SerialName("name")
	val name: String,

	@SerialName("email")
	val email: String
)