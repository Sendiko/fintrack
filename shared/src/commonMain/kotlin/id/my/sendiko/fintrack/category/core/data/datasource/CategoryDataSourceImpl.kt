package id.my.sendiko.fintrack.category.core.data.datasource

import id.my.sendiko.fintrack.category.core.data.dto.DeleteCategoryResponse
import id.my.sendiko.fintrack.category.core.data.dto.GetCategoriesResponse
import id.my.sendiko.fintrack.category.core.data.dto.SaveCategoryRequest
import id.my.sendiko.fintrack.category.core.data.dto.SaveCategoryResponse
import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class CategoryDataSourceImpl(
    private val client: HttpClient
) : CategoryDataSource {
    override suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote> {
        return safeCall<GetCategoriesResponse> {
            client.get("categories")
        }
    }

    override suspend fun postCategory(request: SaveCategoryRequest): Result<SaveCategoryResponse, DataError.Remote> {
        return safeCall<SaveCategoryResponse> {
            client.post("categories") {
                setBody(request)
            }
        }
    }

    override suspend fun putCategory(
        id: String,
        request: SaveCategoryRequest
    ): Result<SaveCategoryResponse, DataError.Remote> {
        return safeCall<SaveCategoryResponse> {
            client.put("categories/$id") {
                setBody(request)
            }
        }
    }

    override suspend fun deleteCategory(id: String): Result<DeleteCategoryResponse, DataError.Remote> {
        return safeCall<DeleteCategoryResponse> {
            client.delete("categories/$id")
        }
    }
}