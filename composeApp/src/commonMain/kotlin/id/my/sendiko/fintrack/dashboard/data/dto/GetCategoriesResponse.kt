package id.my.sendiko.fintrack.dashboard.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetCategoriesResponse(

	@SerialName("message")
	val message: String,

	@SerialName("categories")
	val categories: List<CategoryItem>,

	@SerialName("status")
	val status: Int
)