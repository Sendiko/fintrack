package id.my.sendiko.fintrack.auth.login.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginRequest(

	@SerialName("password")
	val password: String,

	@SerialName("name")
	val name: String
)