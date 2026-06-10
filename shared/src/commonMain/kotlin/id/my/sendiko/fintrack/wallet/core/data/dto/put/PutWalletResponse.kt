package id.my.sendiko.fintrack.wallet.core.data.dto.put

import id.my.sendiko.fintrack.wallet.core.data.dto.WalletDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutWalletResponse(

    @SerialName("wallet")
    val wallet: WalletDto,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)