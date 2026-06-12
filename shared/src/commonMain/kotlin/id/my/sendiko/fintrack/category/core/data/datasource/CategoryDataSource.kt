package id.my.sendiko.fintrack.category.core.data.datasource

import id.my.sendiko.fintrack.category.core.data.dto.DeleteCategoryResponse
import id.my.sendiko.fintrack.category.core.data.dto.GetCategoriesResponse
import id.my.sendiko.fintrack.category.core.data.dto.SaveCategoryRequest
import id.my.sendiko.fintrack.category.core.data.dto.SaveCategoryResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface CategoryDataSource {

    suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote>

    suspend fun postCategory(request: SaveCategoryRequest): Result<SaveCategoryResponse, DataError.Remote>

    suspend fun putCategory(
        id: String,
        request: SaveCategoryRequest
    ): Result<SaveCategoryResponse, DataError.Remote>

    suspend fun deleteCategory(id: String): Result<DeleteCategoryResponse, DataError.Remote>

}