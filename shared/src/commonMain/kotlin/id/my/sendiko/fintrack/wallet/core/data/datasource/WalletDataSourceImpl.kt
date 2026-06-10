package id.my.sendiko.fintrack.wallet.core.data.datasource

import id.my.sendiko.fintrack.core.network.safeCall
import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.wallet.core.data.dto.delete.DeleteWalletResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.put.PutWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.put.PutWalletResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class WalletDataSourceImpl(
    private val client: HttpClient
) : WalletDataSource {

    override suspend fun getWallets(): Result<GetWalletsResponse, DataError.Remote> {
        return safeCall<GetWalletsResponse> {
            client.get("wallets")
        }
    }

    override suspend fun getWallet(id: String): Result<GetWalletResponse, DataError.Remote> {
        return safeCall<GetWalletResponse> {
            client.get("wallets/$id")
        }
    }

    override suspend fun postWallet(request: PostWalletRequest): Result<PostWalletResponse, DataError.Remote> {
        return safeCall<PostWalletResponse> {
            client.post("wallets") {
                setBody(request)
            }
        }
    }

    override suspend fun putWallet(
        id: String,
        request: PutWalletRequest
    ): Result<PutWalletResponse, DataError.Remote> {
        return safeCall<PutWalletResponse> {
            client.put("wallets/$id") {
                setBody(request)
            }
        }
    }

    override suspend fun deleteWallet(id: String): Result<DeleteWalletResponse, DataError.Remote> {
        return safeCall<DeleteWalletResponse> {
            client.delete("wallets/$id")
        }
    }

}