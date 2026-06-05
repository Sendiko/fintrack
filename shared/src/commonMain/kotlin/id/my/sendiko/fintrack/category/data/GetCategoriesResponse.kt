package id.my.sendiko.fintrack.category.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetCategoriesResponse(

	@SerialName("categories")
	val categories: List<CategoriesItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)