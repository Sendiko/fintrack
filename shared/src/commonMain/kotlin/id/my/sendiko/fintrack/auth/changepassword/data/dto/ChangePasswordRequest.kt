package id.my.sendiko.fintrack.auth.changepassword.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChangePasswordRequest(

	@SerialName("password")
	val password: String
)
