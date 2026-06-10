package id.my.sendiko.fintrack.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Serializable
data object RegisterDestination

@Serializable
data object LoginDestination

@Serializable
data object ChangePasswordDestination

@Serializable
data object DashboardDestination

@Serializable
data class FormWalletDestination(val id: String = "")

@Serializable
data object WalletListDestination

@Serializable
data class FormTransactionDestination(val type: String, val id: String = "")

@Serializable
data object ListTransactionDestination