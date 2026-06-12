package id.my.sendiko.fintrack.category.core.data

import id.my.sendiko.fintrack.category.core.data.datasource.CategoryDataSource
import id.my.sendiko.fintrack.category.core.data.dto.SaveCategoryRequest
import id.my.sendiko.fintrack.category.core.domain.CategoryRepository
import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.core.preferences.PreferenceRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource,
    private val preferences: PreferenceRepository
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<CategoryWithTransactions>, DataError.Remote> {
        return when (val response = dataSource.getCategories()) {
            is Result.Success -> Result.Success(response.data.categories.map { it.toDomainWithTransaction() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun saveCategory(
        id: String,
        userId: String,
        name: String
    ): Result<Category, DataError.Remote> {
        val request = SaveCategoryRequest(
            name = name,
            userId = userId
        )
        return if (id.isBlank()) {
            when (val response = dataSource.postCategory(request)) {
                is Result.Success -> Result.Success(response.data.category.toDomain())
                is Result.Error -> Result.Error(response.error)
            }
        } else {
            when (val response = dataSource.putCategory(id, request)) {
                is Result.Success -> Result.Success(response.data.category.toDomain())
                is Result.Error -> Result.Error(response.error)
            }
        }
    }

    override suspend fun deleteCategory(id: String): Result<String, DataError.Remote> {
        return when (val response = dataSource.deleteCategory(id)) {
            is Result.Success -> Result.Success(response.data.message)
            is Result.Error -> Result.Error(response.error)
        }
    }

    override fun getUserId(): Flow<String> {
        return preferences.getUserId()
    }
}