package id.my.sendiko.fintrack.budget.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BudgetsItem(

	@SerialName("summary")
	val summary: Summary,

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("amount")
	val amount: Int,

	@SerialName("endDate")
	val endDate: String,

	@SerialName("id")
	val id: String,

	@SerialName("userId")
	val userId: String,

	@SerialName("startDate")
	val startDate: String,

	@SerialName("updatedAt")
	val updatedAt: String
)