package id.my.sendiko.fintrack.transaction.core.domain

interface ReceiptParser {
    fun parse(rawText: String): ScannedReceipt
}