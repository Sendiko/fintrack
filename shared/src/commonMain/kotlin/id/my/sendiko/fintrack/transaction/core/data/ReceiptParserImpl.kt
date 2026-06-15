package id.my.sendiko.fintrack.transaction.core.data

import id.my.sendiko.fintrack.transaction.core.domain.ReceiptParser
import id.my.sendiko.fintrack.transaction.core.domain.ScannedReceipt
import id.my.sendiko.fintrack.transaction.core.domain.parser.GenericParser
import id.my.sendiko.fintrack.transaction.core.domain.parser.GopayParser
import id.my.sendiko.fintrack.transaction.core.domain.parser.JagoParser
import id.my.sendiko.fintrack.transaction.core.domain.parser.WondrParser

class ReceiptParserImpl : ReceiptParser {

    private val parsers = listOf(
        GopayParser(),
        JagoParser(),
        WondrParser(),
        GenericParser()
    )

    override fun parse(rawText: String): ScannedReceipt {

        return parsers
            .firstOrNull { it.canHandle(rawText) }
            ?.parse(rawText)
            ?: GenericParser().parse(rawText)
    }
}