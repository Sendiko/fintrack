package id.my.sendiko.fintrack.budget.domain.model

data class Budget(
    val id: String,
    val amount: Int,
    val startDate: String,
    val endDate: String,
    val summary: Summary
)
