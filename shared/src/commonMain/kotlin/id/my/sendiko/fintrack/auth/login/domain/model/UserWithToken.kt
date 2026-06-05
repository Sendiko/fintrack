package id.my.sendiko.fintrack.auth.login.domain.model

import id.my.sendiko.fintrack.auth.core.domain.User

data class UserWithToken(
    val data: User,
    val token: String
)
