package id.my.sendiko.fintrack.core.presentation.rupiah

fun Double.toRupiah(): String {
    val rounded = this.toLong()  // Assuming no decimals needed
    val reversed = rounded.toString().reversed()
    val grouped = reversed.chunked(3).joinToString(".")
    val formatted = grouped.reversed()
    return "Rp. $formatted"
}
