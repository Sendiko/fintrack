package id.my.sendiko.fintrack.category.core.data

import id.my.sendiko.fintrack.category.core.data.datasource.CategoryDataSource
import id.my.sendiko.fintrack.category.core.domain.CategoryRepository
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<CategoryWithTransactions>, DataError.Remote> {
        return when (val response = dataSource.getCategories()) {
            is Result.Success -> Result.Success(response.data.categories.map { it.toDomainWithTransaction() })
            is Result.Error -> Result.Error(response.error)
        }
    }
}