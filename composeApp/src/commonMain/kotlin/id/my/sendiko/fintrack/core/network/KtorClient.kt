package id.my.sendiko.fintrack.core.network

import io.ktor.client.HttpClient

class KtorClient(
    val client: HttpClient
): ApiService {

}