package id.my.sendiko.fintrack.transaction.core.domain

data class ScannedReceipt(
    val amount: String,
    val date: String,
    val receiver: String
)

interface ReceiptStrategy {

    fun canHandle(rawText: String): Boolean
    fun parse(rawText: String): ScannedReceipt

}