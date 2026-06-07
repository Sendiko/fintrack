package id.my.sendiko.fintrack.category.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("userId")
	val userId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)