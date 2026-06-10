package id.my.sendiko.fintrack.wallet.core.data.datasource

import id.my.sendiko.fintrack.core.network.utils.DataError
import id.my.sendiko.fintrack.core.network.utils.Result
import id.my.sendiko.fintrack.wallet.core.data.dto.delete.DeleteWalletResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.getdetails.GetWalletsResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.post.PostWalletResponse
import id.my.sendiko.fintrack.wallet.core.data.dto.put.PutWalletRequest
import id.my.sendiko.fintrack.wallet.core.data.dto.put.PutWalletResponse

interface WalletDataSource {

    suspend fun getWallets(): Result<GetWalletsResponse, DataError.Remote>

    suspend fun getWallet(id: String): Result<GetWalletResponse, DataError.Remote>

    suspend fun postWallet(request: PostWalletRequest): Result<PostWalletResponse, DataError.Remote>

    suspend fun putWallet(
        id: String,
        request: PutWalletRequest
    ): Result<PutWalletResponse, DataError.Remote>

    suspend fun deleteWallet(id: String): Result<DeleteWalletResponse, DataError.Remote>

}