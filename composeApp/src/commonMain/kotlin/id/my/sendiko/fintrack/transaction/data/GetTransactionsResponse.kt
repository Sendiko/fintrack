package id.my.sendiko.fintrack.transaction.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetTransactionsResponse(

	@SerialName("message")
	val message: String,

	@SerialName("transactions")
	val transactions: List<TransactionsItem>,

	@SerialName("status")
	val status: Int
)