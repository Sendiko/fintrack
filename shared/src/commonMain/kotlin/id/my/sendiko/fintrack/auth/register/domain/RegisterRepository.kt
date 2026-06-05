package id.my.sendiko.fintrack.auth.register.domain

import id.my.sendiko.fintrack.auth.core.domain.User
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface RegisterRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User, DataError.Remote>
}