package id.my.sendiko.fintrack.transaction.core.domain

expect class OcrEngine() {
    suspend fun extractTextFromImage(imageBytes: ByteArray): String
}