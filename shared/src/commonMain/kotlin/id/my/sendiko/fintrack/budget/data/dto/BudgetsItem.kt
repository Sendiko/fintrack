package id.my.sendiko.fintrack.budget.data.dto

import id.my.sendiko.fintrack.budget.domain.model.Budget
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BudgetsItem(

	@SerialName("summary")
	val summaryItem: SummaryItem,

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
) {
	fun toDomain() = Budget(
        id = id,
        amount = amount,
        startDate = startDate,
        endDate = endDate,
        summary = summaryItem
    )
}