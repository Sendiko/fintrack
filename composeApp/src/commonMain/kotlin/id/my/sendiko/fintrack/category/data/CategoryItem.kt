package id.my.sendiko.fintrack.category.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CategoryItem(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("transactions")
	val transactions: List<TransactionsItem>,

	@SerialName("userId")
	val userId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)