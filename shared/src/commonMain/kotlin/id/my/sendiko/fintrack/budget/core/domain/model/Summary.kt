package id.my.sendiko.fintrack.budget.core.domain.model

data class Summary(
    val totalAmount: Int,
    val remainingAmount: Int,
    val spent: Int,
    val remainingDays: Int,
    val spentToday: Int,
    val remainingToday: Double,
    val isActive: Boolean,
    val todayBudget: Double,
    val dailyPercentageUsed: Int,
    val totalPercentageUsed: Int
)
