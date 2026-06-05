package id.my.sendiko.fintrack.core.network

import id.my.sendiko.fintrack.core.network.utils.ApiErrorResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.RequestTimeout)
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.NoInternet)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.Serialization)
    } catch (e: JsonConvertException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.Serialization)
    } catch (e: ConnectTimeoutException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.NoInternet)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        e.printStackTrace()
        return Result.Error(DataError.Remote.Unknown)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                e.printStackTrace()
                Result.Error(DataError.Remote.Serialization)
            }
        }
        else -> Result.Error(parseError(response, response.status.value))
    }
}

suspend fun parseError(response: HttpResponse, fallback: Int): DataError.Remote {
    return try {
        val errorResponse = response.body<ApiErrorResponse>()

        DataError.Remote.ApiErrorMessage(errorResponse.message)
    } catch (e: Exception) {
        e.printStackTrace()
        when (fallback) {
            400 -> DataError.Remote.BadRequest
            401 -> DataError.Remote.Unauthorized
            403 -> DataError.Remote.Forbidden
            404 -> DataError.Remote.NotFound
            408 -> DataError.Remote.RequestTimeout
            409 -> DataError.Remote.Conflict
            429 -> DataError.Remote.TooManyRequests
            in 500..599 -> DataError.Remote.ServerError
            else -> DataError.Remote.Unknown
        }
    }
}