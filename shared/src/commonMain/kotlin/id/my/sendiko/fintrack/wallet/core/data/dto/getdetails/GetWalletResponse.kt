package id.my.sendiko.fintrack.wallet.core.data.dto.getdetails

import id.my.sendiko.fintrack.wallet.core.data.dto.WalletDtoWithTransaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWalletResponse(

    @SerialName("wallet")
    val wallet: WalletDtoWithTransaction,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)