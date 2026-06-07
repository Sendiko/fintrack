package id.my.sendiko.fintrack.category.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetCategoriesResponse(

    @SerialName("categories")
	val categories: List<CategoryDtoWithTransactions>,

    @SerialName("message")
	val message: String,

    @SerialName("status")
	val status: Int
)