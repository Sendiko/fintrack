package id.my.sendiko.fintrack.budget.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Summary(

	@SerialName("totalAmount")
	val totalAmount: Int,

	@SerialName("remainingAmount")
	val remainingAmount: Int,

	@SerialName("spent")
	val spent: Int,

	@SerialName("remainingDays")
	val remainingDays: Int,

	@SerialName("spentToday")
	val spentToday: Int,

	@SerialName("remainingToday")
	val remainingToday: Double,

	@SerialName("isActive")
	val isActive: Boolean,

	@SerialName("todayBudget")
	val todayBudget: Double
)