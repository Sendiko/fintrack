package id.my.sendiko.fintrack.auth.core.domain

import id.my.sendiko.fintrack.auth.register.data.dto.UserData

data class User(
    val userId: String,
    val name: String,
    val email: String,
) {
    companion object {
        fun fromResponse(userData: UserData): User {
            return User(
                userId = userData.id,
                name = userData.name,
                email = userData.email
            )
        }
    }
}
