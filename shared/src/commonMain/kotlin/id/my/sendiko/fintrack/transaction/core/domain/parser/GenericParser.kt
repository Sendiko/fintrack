package id.my.sendiko.fintrack.transaction.core.domain.parser

import id.my.sendiko.fintrack.transaction.core.domain.ReceiptStrategy
import id.my.sendiko.fintrack.transaction.core.domain.ScannedReceipt

class GenericParser : ReceiptStrategy {
    override fun canHandle(rawText: String): Boolean = true

    override fun parse(rawText: String): ScannedReceipt {
        val lines = rawText
            .split("\n")
            .map { it.trim() }
            .filter { it.isNotBlank() }

        println("[RECEIPT] Generic Parser Executed.")

        return ScannedReceipt(
            amount = parseAmount(rawText),
            date = parseDate(rawText, lines),
            receiver = parseReceiver(lines)
        )
    }

    private fun parseAmount(rawText: String): String {
        val amountPatterns = listOf(
            """(?:Rp\.?|IDR)\s*([\d.,]+)""",
            """(?:Total|TOTAL|Amount|Nominal)\s*:?\s*(?:Rp\.?|IDR)?\s*([\d.,]+)""",
            """([\d.]+,\d{2})"""
        )

        val amounts = mutableListOf<Long>()

        amountPatterns.forEach { pattern ->
            pattern.toRegex(RegexOption.IGNORE_CASE)
                .findAll(rawText)
                .forEach { match ->

                    val digitsOnly = match.groupValues[1]
                        .replace(Regex("""\D"""), "")

                    digitsOnly.toLongOrNull()?.let {
                        amounts.add(it)
                    }
                }
        }

        return amounts.maxOrNull()?.toString() ?: ""
    }

    private fun parseDate(
        rawText: String,
        lines: List<String>
    ): String {

        val labelPatterns = listOf(
            "Transaction date",
            "Date and time",
            "Tanggal",
            "Waktu transaksi",
            "Transaction Time"
        )

        for (i in lines.indices) {
            if (labelPatterns.any { lines[i].contains(it, true) }) {
                lines.getOrNull(i + 1)?.let {
                    if (it.isNotBlank()) {
                        return it
                    }
                }
            }
        }

        val regexPatterns = listOf(
            """\d{2}[-/]\d{2}[-/]\d{4}\s+\d{2}:\d{2}(?::\d{2})?""",
            """\d{2}[-/]\d{2}[-/]\d{4}""",
            """\d{1,2}\s+[A-Za-z]{3,}\s+\d{4}(?:\s+\d{2}:\d{2})?""",
            """\d{4}-\d{2}-\d{2}\s+\d{2}:\d{2}(?::\d{2})?"""
        )

        regexPatterns.forEach { pattern ->
            pattern.toRegex()
                .find(rawText)
                ?.groupValues
                ?.firstOrNull()
                ?.let { return it }
        }

        return "Unknown Date"
    }

    private fun parseReceiver(lines: List<String>): String {

        val receiverLabels = listOf(
            "To",
            "Transfer To",
            "Penerima",
            "Transfer Ke",
            "Merchant",
            "Receiver",
            "Beneficiary",
            "Acquirer Name",
            "Recipient"
        )

        for (i in lines.indices) {
            if (receiverLabels.any { lines[i].contains(it, true) }) {

                val candidate = lines.getOrNull(i + 1)

                if (candidate != null &&
                    candidate.isValidReceiver()
                ) {
                    return candidate
                }
            }
        }

        // Heuristic fallback:
        // Search the top section for the first human-readable text
        val headerLines = lines.take(8)

        for (line in headerLines) {

            if (line.isValidReceiver()) {
                return line
            }
        }

        return "Unknown Receiver"
    }

    private fun String.isValidReceiver(): Boolean {

        val blacklist = listOf(
            "success",
            "berhasil",
            "transaction",
            "transaksi",
            "receipt",
            "detail",
            "rincian",
            "account",
            "rekening",
            "saldo",
            "date",
            "tanggal",
            "time",
            "waktu",
            "reference",
            "ref",
            "invoice",
            "rp",
            "idr",
            "bank",
            "payment",
            "transfer"
        )

        if (length < 3) return false

        if (any { it.isDigit() } && count { it.isDigit() } > length / 2) {
            return false
        }

        if (blacklist.any { contains(it, true) }) {
            return false
        }

        return true
    }
}