package id.my.sendiko.fintrack.budget.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostBudgetResponse(

	@SerialName("amount")
	val amount: Int,

	@SerialName("endDate")
	val endDate: String,

	@SerialName("startDate")
	val startDate: String
)