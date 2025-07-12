package id.my.sendiko.fintrack.wallet.create.presentation

import id.my.sendiko.fintrack.wallet.core.domain.WalletType

data class CreateWalletState(
    val token: String = "",
    val isLoading: Boolean = false,
    val message: String = "",
    val userId: String = "",
    val stage: Int = 1,
    val name: String = "",
    val purpose: String = "",
    val type: String = "",
    val walletTypeList: List<WalletType> = WalletType.entries,
    val dropdownExpanding: Boolean = false,
    val number: Double = 0.0,
)
