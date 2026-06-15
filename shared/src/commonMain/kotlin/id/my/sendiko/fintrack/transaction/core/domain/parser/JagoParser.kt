package id.my.sendiko.fintrack.transaction.core.domain.parser

import id.my.sendiko.fintrack.transaction.core.domain.ReceiptStrategy
import id.my.sendiko.fintrack.transaction.core.domain.ScannedReceipt

class JagoParser : ReceiptStrategy {

    override fun canHandle(rawText: String): Boolean {
        return rawText.contains("Transaction ID", true) ||
                rawText.contains("jago", ignoreCase = true) ||
                rawText.contains("Account Source", true)
    }

    override fun parse(rawText: String): ScannedReceipt {

        val lines = rawText.lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }

        println("[RECEIPT] Jago Parser Executed.")

        val amount = lines.firstOrNull {
            it.matches("""Rp[\d.]+""".toRegex())
        }?.replace("Rp", "")
            ?.replace(".", "")
            ?: ""

        var receiver = ""

        for (i in 0 until minOf(5, lines.size)) {

            val line = lines[i]

            if (!line.contains("Jago", true) &&
                !line.contains("Rp") &&
                !line.contains("Success", true)
            ) {

                receiver = line
                break
            }
        }

        var date = ""

        lines.forEachIndexed { index, line ->

            if (line.contains("Transaction date", true)) {

                date = lines.getOrNull(index + 1) ?: ""

            }
        }

        return ScannedReceipt(
            amount = amount,
            date = date,
            receiver = receiver
        )
    }
}