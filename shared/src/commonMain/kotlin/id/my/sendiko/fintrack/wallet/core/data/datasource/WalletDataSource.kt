package id.my.sendiko.fintrack.wallet.core.data.datasource

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletResponse

interface WalletDataSource {

    suspend fun getWallets(): Result<GetWalletsResponse, DataError.Remote>

    suspend fun postWallet(request: PostWalletRequest): Result<PostWalletResponse, DataError.Remote>

}