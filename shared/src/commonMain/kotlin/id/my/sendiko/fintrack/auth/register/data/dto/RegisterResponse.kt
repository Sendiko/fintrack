package id.my.sendiko.fintrack.auth.register.data.dto

import id.my.sendiko.fintrack.auth.core.data.dto.UserDto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RegisterResponse(

    @SerialName("message")
	val message: String,

    @SerialName("user")
	val userDto: UserDto,

    @SerialName("status")
	val status: Int
)
