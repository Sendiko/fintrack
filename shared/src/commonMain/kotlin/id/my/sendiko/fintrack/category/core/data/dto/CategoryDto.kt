package id.my.sendiko.fintrack.category.core.data.dto

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.core.presentation.icon.toCategoryImageVector
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

	@SerialName("icon")
	val icon: String,

	@SerialName("updatedAt")
	val updatedAt: String
) {
	fun toDomain() = Category(
        id = id,
        name = name,
		icon = icon
    )
}