package id.my.sendiko.fintrack.auth.register.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(

    @SerialName("message")
    val message: String,

    @SerialName("user")
    val userDto: UserDtoWithoutToken,

    @SerialName("status")
    val status: Int
)
