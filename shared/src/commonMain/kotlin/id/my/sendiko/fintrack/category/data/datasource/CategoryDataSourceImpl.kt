package id.my.sendiko.fintrack.category.data.datasource

import id.my.sendiko.fintrack.category.data.dto.GetCategoriesResponse
import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CategoryDataSourceImpl(
    private val client: HttpClient
) : CategoryDataSource {
    override suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote> {
        return safeCall<GetCategoriesResponse> {
            client.get("categories")
        }
    }
}