package id.my.sendiko.fintrack.budget.core.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostBudgetRequest(

	@SerialName("amount")
	val amount: Int,

	@SerialName("endDate")
	val endDate: String,

	@SerialName("startDate")
	val startDate: String,

	@SerialName("walletId")
	val walletId: String
)