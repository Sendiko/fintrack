package id.my.sendiko.fintrack.auth.changepassword.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChangePasswordResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)
