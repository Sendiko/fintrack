package id.my.sendiko.fintrack.auth.changepassword.data.dto

import id.my.sendiko.fintrack.auth.core.domain.User
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SearchUserResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val userDto: UserDto,

	@SerialName("status")
	val status: Int
)

@Serializable
data class UserDto(

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("email")
	val email: String
) {
	fun toDomain() = User(
        userId = id,
        name = name,
        email = email
    )
}
