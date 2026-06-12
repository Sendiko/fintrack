package id.my.sendiko.fintrack.category.core.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SaveCategoryRequest(

	@SerialName("name")
	val name: String,

	@SerialName("userId")
	val userId: String
)
