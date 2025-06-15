package id.my.sendiko.fintrack.wallet.domain

sealed class WalletType(name: String) {
    class Card: WalletType("card")
    class Emoney: WalletType("e-money")
    class Cash: WalletType("cash")
}