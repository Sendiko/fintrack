package id.my.sendiko.fintrack.budget.core.domain

import id.my.sendiko.fintrack.budget.core.domain.model.Budget
import id.my.sendiko.fintrack.budget.core.domain.model.Summary
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result

interface BudgetRepository {

    suspend fun getBudgets(): Result<List<Budget>, DataError.Remote>

    suspend fun getBudget(id: String): Result<Budget, DataError.Remote>

    suspend fun getTodayBudget(): Result<Summary, DataError.Remote>

    suspend fun postBudget(amount: Int, startDate: String, endDate: String, walletId: String): Result<Budget, DataError.Remote>

}