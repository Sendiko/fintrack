package id.my.sendiko.fintrack.transaction.core.domain.parser

import id.my.sendiko.fintrack.transaction.core.domain.ReceiptStrategy
import id.my.sendiko.fintrack.transaction.core.domain.ScannedReceipt

class WondrParser : ReceiptStrategy {

    override fun canHandle(rawText: String): Boolean {

        return rawText.contains("wondr", true) ||
                rawText.contains("Pembayaran QRIS berhasil", true)
    }

    override fun parse(rawText: String): ScannedReceipt {

        val lines = rawText.lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }

        val amount = lines
            .mapNotNull {

                """Rp([\d.]+)"""
                    .toRegex()
                    .find(it)
                    ?.groupValues
                    ?.get(1)
                    ?.replace(".", "")
                    ?.toLongOrNull()

            }
            .maxOrNull()
            ?.toString()
            ?: ""

        var receiver = ""

        lines.forEachIndexed { index, line ->

            if (line.equals("Penerima", true)) {

                receiver = lines.getOrNull(index + 1) ?: ""

            }

        }

        val date = lines.firstOrNull {

            Regex(
                """\d{2}\s+[A-Za-z]{3}\s+\d{4}.*WIB"""
            ).containsMatchIn(it)

        } ?: ""

        return ScannedReceipt(
            amount = amount,
            date = date,
            receiver = receiver
        )
    }
}