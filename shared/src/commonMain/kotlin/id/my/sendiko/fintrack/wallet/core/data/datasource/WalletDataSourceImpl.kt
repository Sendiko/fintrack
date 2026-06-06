package id.my.sendiko.fintrack.wallet.core.data.datasource

import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class WalletDataSourceImpl(
    private val client: HttpClient
) : WalletDataSource {

    override suspend fun getWallets(): Result<GetWalletsResponse, DataError.Remote> {
        return safeCall<GetWalletsResponse> {
            client.get("wallets")
        }
    }

    override suspend fun postWallet(request: PostWalletRequest): Result<PostWalletResponse, DataError.Remote> {
        return safeCall<PostWalletResponse> {
            client.post("wallets") {
                setBody(request)
            }
        }
    }

}