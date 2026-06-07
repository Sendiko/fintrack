package id.my.sendiko.fintrack.category.data.dto

import id.my.sendiko.fintrack.transaction.data.dto.TransactionsDto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CategoryDtoWithTransactions(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("transactions")
	val transactions: List<TransactionsDto>,

	@SerialName("userId")
	val userId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)