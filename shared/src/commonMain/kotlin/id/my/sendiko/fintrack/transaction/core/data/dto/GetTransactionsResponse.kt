package id.my.sendiko.fintrack.transaction.core.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetTransactionsResponse(

	@SerialName("message")
	val message: String,

	@SerialName("transactions")
	val transactions: List<TransactionsDtoWithWallet>,

	@SerialName("status")
	val status: Int
)