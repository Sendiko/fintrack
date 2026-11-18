package id.my.sendiko.fintrack.budget.core.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetBudgetsResponse(

	@SerialName("budgets")
	val budgets: List<BudgetsItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)