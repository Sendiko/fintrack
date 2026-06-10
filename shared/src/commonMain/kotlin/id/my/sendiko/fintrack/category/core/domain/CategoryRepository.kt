package id.my.sendiko.fintrack.category.core.domain

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface CategoryRepository {

    suspend fun getCategories(): Result<List<Category>, DataError.Remote>

}