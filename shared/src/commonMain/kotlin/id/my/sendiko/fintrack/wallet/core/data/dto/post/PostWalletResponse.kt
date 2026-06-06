package id.my.sendiko.fintrack.wallet.core.data.dto.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostWalletResponse(

    @SerialName("wallet")
    val wallet: PostWalletItem,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)