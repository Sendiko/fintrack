package id.my.sendiko.fintrack.budget.core.data.datasource

import id.my.sendiko.fintrack.budget.core.data.dto.GetBudgetResponse
import id.my.sendiko.fintrack.budget.core.data.dto.GetBudgetsResponse
import id.my.sendiko.fintrack.budget.core.data.dto.GetTodayBudget
import id.my.sendiko.fintrack.budget.core.data.dto.PostBudgetRequest
import id.my.sendiko.fintrack.budget.core.data.dto.PostBudgetResponse
import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class BudgetRemoteDataSourceImpl(
    private val client: HttpClient,
) : BudgetRemoteDataSource {
    override suspend fun getBudgets(): Result<GetBudgetsResponse, DataError.Remote> {
        return safeCall<GetBudgetsResponse> {
            client.get("budgets")
        }
    }

    override suspend fun getBudget(id: String): Result<GetBudgetResponse, DataError.Remote> {
        return safeCall<GetBudgetResponse> {
            client.get("budgets/$id")
        }
    }

    override suspend fun getTodayBudget(): Result<GetTodayBudget, DataError.Remote> {
        return safeCall<GetTodayBudget> {
            client.get("budgets/today")
        }
    }

    override suspend fun postBudget(request: PostBudgetRequest): Result<PostBudgetResponse, DataError.Remote> {
        return safeCall<PostBudgetResponse> {
            client.post("budgets") {
                setBody(request )
            }
        }
    }
}