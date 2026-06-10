package id.my.sendiko.fintrack.wallet.core.data.dto.delete

import id.my.sendiko.fintrack.wallet.core.data.dto.WalletDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteWalletResponse(
    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)