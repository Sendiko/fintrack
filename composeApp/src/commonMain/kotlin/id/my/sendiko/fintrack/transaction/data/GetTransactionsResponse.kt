package id.my.sendiko.fintrack.transaction.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetTransactionsResponse(

	@SerialName("message")
	val message: String,

	@SerialName("transaction")
	val transaction: List<TransactionItem>,

	@SerialName("status")
	val status: Int
)