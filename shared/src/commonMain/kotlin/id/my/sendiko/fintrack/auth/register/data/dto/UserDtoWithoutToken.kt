package id.my.sendiko.fintrack.auth.register.data.dto

import id.my.sendiko.fintrack.auth.core.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDtoWithoutToken(

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
) {
    fun toDomain() = User(
        userId = id,
        name = name,
        email = email
    )
}