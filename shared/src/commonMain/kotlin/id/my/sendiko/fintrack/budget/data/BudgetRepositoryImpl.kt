package id.my.sendiko.fintrack.budget.data

import id.my.sendiko.fintrack.budget.data.datasource.BudgetRemoteDataSource
import id.my.sendiko.fintrack.budget.data.dto.PostBudgetRequest
import id.my.sendiko.fintrack.budget.domain.BudgetRepository
import id.my.sendiko.fintrack.budget.domain.model.Budget
import id.my.sendiko.fintrack.budget.domain.model.Summary
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

class BudgetRepositoryImpl(
    private val remoteDataSource: BudgetRemoteDataSource
) : BudgetRepository {
    override suspend fun getBudgets(): Result<List<Budget>, DataError.Remote> {
        return when (val response = remoteDataSource.getBudgets()) {
            is Result.Success -> Result.Success(response.data.budgets.map { it.toDomain() })
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun getTodayBudget(): Result<Summary, DataError.Remote> {
        return when (val response = remoteDataSource.getTodayBudget()) {
            is Result.Success -> Result.Success(response.data.summaryItem.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun postBudget(
        amount: Int,
        startDate: String,
        endDate: String
    ): Result<Budget, DataError.Remote> {
        val request = PostBudgetRequest(
            amount = amount,
            startDate = startDate,
            endDate = endDate
        )
        return when (val response = remoteDataSource.postBudget(request)) {
            is Result.Success -> Result.Success(response.data.budget.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }
}