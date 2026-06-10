package id.my.sendiko.fintrack.category.core.data.datasource

import id.my.sendiko.fintrack.category.core.data.dto.GetCategoriesResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface CategoryDataSource {

    suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote>
    
}