package id.my.sendiko.fintrack.category.data

import id.my.sendiko.fintrack.category.data.datasource.CategoryDataSource
import id.my.sendiko.fintrack.category.domain.CategoryRepository
import id.my.sendiko.fintrack.category.domain.model.Category
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>, DataError.Remote> {
        return when (val response = dataSource.getCategories()) {
            is Result.Success -> Result.Success(response.data.categories.map { it.toDomain() })
            is Result.Error -> Result.Error(response.error)
        }
    }
}