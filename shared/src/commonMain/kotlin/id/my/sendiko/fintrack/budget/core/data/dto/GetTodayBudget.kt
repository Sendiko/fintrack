package id.my.sendiko.fintrack.budget.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTodayBudget(
    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int,

    @SerialName("summary")
    val summaryItem: SummaryItem
)
