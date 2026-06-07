package id.my.sendiko.fintrack.transaction.data.dto

import id.my.sendiko.fintrack.transaction.domain.Transaction
import id.my.sendiko.fintrack.transaction.domain.TransactionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionsDto(

	@SerialName("walletId")
	val walletId: String,

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("amount")
	val amount: Int,

	@SerialName("deletedAt")
	val deletedAt: String?,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("type")
	val type: String,

	@SerialName("userId")
	val userId: String,

	@SerialName("categoryId")
	val categoryId: String,

	@SerialName("updatedAt")
	val updatedAt: String
) {
	fun toDomain() = Transaction(
        id = id,
        name = name,
        amount = amount.toFloat(),
        type = TransactionType.valueOf(type.uppercase()),
        categoryId = categoryId,
        userId = userId,
        walletId = walletId
    )
}