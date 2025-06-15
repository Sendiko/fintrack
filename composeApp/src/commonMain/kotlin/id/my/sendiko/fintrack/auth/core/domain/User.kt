package id.my.sendiko.fintrack.auth.core.domain

import id.my.sendiko.fintrack.auth.core.data.UserItem


data class User(
    val userId: String,
    val name: String,
    val email: String,
) {
    companion object {
        fun fromResponse(userItem: UserItem): User {
            return User(
                userId = userItem.id,
                name = userItem.name,
                email = userItem.email
            )
        }
    }
}
