package id.my.sendiko.fintrack.category.data.datasource

import id.my.sendiko.fintrack.category.data.dto.GetCategoriesResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface CategoryDataSource {

    suspend fun getCategories(): Result<GetCategoriesResponse, DataError.Remote>
    
}