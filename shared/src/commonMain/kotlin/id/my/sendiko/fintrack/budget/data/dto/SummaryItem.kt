package id.my.sendiko.fintrack.budget.data.dto

import id.my.sendiko.fintrack.budget.domain.model.Summary
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SummaryItem(

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
	val todayBudget: Double,

	@SerialName("dailyPercentageUsed")
	val dailyPercentageUsed: Int,

	@SerialName("totalPercentageUsed")
	val totalPercentageUsed: Int,
) {
	fun toDomain() = Summary(
		totalAmount = totalAmount,
		remainingAmount = remainingAmount,
		spent = spent,
		remainingDays = remainingDays,
		spentToday = spentToday,
		remainingToday = remainingToday,
		isActive = isActive,
		todayBudget = todayBudget,
		dailyPercentageUsed = dailyPercentageUsed,
		totalPercentageUsed = totalPercentageUsed
	)
}