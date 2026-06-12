package id.my.sendiko.fintrack.category.core.domain

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Result<List<CategoryWithTransactions>, DataError.Remote>

    suspend fun saveCategory(
        id: String,
        userId: String,
        name: String
    ): Result<Category, DataError.Remote>

    suspend fun deleteCategory(id: String): Result<String, DataError.Remote>

    fun getUserId(): Flow<String>

}