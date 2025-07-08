package id.my.sendiko.fintrack.transaction.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TransactionItem(

	@SerialName("walletId")
	val walletId: String,

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("amount")
	val amount: Int,

	@SerialName("deletedAt")
	val deletedAt: String?,

	@SerialName("wallet")
	val wallet: Wallet,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("type")
	val type: String,

	@SerialName("category")
	val category: Category,

	@SerialName("userId")
	val userId: String,

	@SerialName("categoryId")
	val categoryId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)