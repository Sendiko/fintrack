package id.my.sendiko.fintrack.wallet.form.presentation

import id.my.sendiko.fintrack.wallet.core.domain.WalletType

data class FormWalletState(
    val token: String = "",
    val isLoading: Boolean = false,
    val message: String = "",
    val userId: String = "",
    val stage: Int = 1,
    val name: String = "",
    val purpose: String = "",
    val type: String = "",
    val amount: String = "0.0",
    val walletTypeList: List<WalletType> = WalletType.entries,
    val dropdownExpanding: Boolean = false,
    val number: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false
)
