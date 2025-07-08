package id.my.sendiko.fintrack.dashboard.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWalletsResponse(

    @SerialName("wallets")
	val wallets: List<WalletsItem>,

    @SerialName("message")
	val message: String,

    @SerialName("status")
	val status: Int
)