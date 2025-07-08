package id.my.sendiko.fintrack.category.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetCategoriesResponse(

	@SerialName("message")
	val message: String,

	@SerialName("category")
	val category: List<CategoryItem>,

	@SerialName("status")
	val status: Int
)