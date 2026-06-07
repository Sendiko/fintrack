package id.my.sendiko.fintrack.transaction.data.dto

import id.my.sendiko.fintrack.category.data.CategoryDto
import id.my.sendiko.fintrack.wallet.core.data.dto.WalletDto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TransactionsDtoWithWallet(

	@SerialName("walletId")
	val walletId: String,

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("amount")
	val amount: Int,

	@SerialName("deletedAt")
	val deletedAt: String?,

	@SerialName("wallet")
	val wallet: WalletDto,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("type")
	val type: String,

	@SerialName("category")
	val category: CategoryDto,

	@SerialName("userId")
	val userId: String,

	@SerialName("categoryId")
	val categoryId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)