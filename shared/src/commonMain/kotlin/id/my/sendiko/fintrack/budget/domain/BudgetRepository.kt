package id.my.sendiko.fintrack.budget.domain

import id.my.sendiko.fintrack.budget.domain.model.Budget
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface BudgetRepository {

    suspend fun getBudgets(): Result<List<Budget>, DataError.Remote>

    suspend fun postBudget(amount: Int, startDate: String, endDate: String): Result<Budget, DataError.Remote>

}