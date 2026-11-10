package id.my.sendiko.fintrack.budget.data.datasource

import id.my.sendiko.fintrack.budget.data.dto.GetBudgetResponse
import id.my.sendiko.fintrack.budget.data.dto.GetBudgetsResponse
import id.my.sendiko.fintrack.budget.data.dto.GetTodayBudget
import id.my.sendiko.fintrack.budget.data.dto.PostBudgetRequest
import id.my.sendiko.fintrack.budget.data.dto.PostBudgetResponse
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface BudgetRemoteDataSource {

    suspend fun getBudgets(): Result<GetBudgetsResponse, DataError.Remote>

    suspend fun getBudget(id: String): Result<GetBudgetResponse, DataError.Remote>

    suspend fun getTodayBudget(): Result<GetTodayBudget, DataError.Remote>

    suspend fun postBudget(request: PostBudgetRequest): Result<PostBudgetResponse, DataError.Remote>

}