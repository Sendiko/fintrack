package id.my.sendiko.fintrack.transaction.core.domain.parser

import id.my.sendiko.fintrack.transaction.core.domain.ReceiptStrategy
import id.my.sendiko.fintrack.transaction.core.domain.ScannedReceipt

class GopayParser : ReceiptStrategy {

    override fun canHandle(rawText: String): Boolean {

        return rawText.contains("gopay", true) &&
                rawText.contains("Rincian transaksi", true)
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

        val amountIndex = lines.indexOfFirst {

            it.contains("Rp") &&
                    Regex("""Rp[\d.]+""").containsMatchIn(it)

        }

        if (amountIndex != -1) {

            receiver = lines
                .drop(amountIndex + 1)
                .firstOrNull {

                    !it.contains("Rincian", true) &&
                            !it.contains("Status", true)

                }
                ?: ""

        }

        var time = ""
        var date = ""

        lines.forEachIndexed { index, line ->

            when {

                line.equals("Waktu", true) ->
                    time = lines.getOrNull(index + 1) ?: ""

                line.equals("Tanggal", true) ->
                    date = lines.getOrNull(index + 1) ?: ""

            }

        }

        return ScannedReceipt(
            amount = amount,
            date = "$date $time".trim(),
            receiver = receiver
        )
    }
}