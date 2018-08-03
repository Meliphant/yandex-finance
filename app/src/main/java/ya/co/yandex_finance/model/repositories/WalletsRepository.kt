package ya.co.yandex_finance.model.repositories

import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.WalletTypes

class WalletsRepository {

    val wallets = arrayListOf<Wallet>()

    init {
        wallets.add(Wallet(0, "myRubWallet", Currency.RUB, WalletTypes.CASH))
        wallets.add(Wallet(1, "myUsdWallet", Currency.USD, WalletTypes.CARD))

        wallets.add(Wallet(2, "anRubWallet", Currency.RUB, WalletTypes.CASH))
        wallets.add(Wallet(3, "anUsdWallet", Currency.USD, WalletTypes.CARD))
    }

    //get total money in a wallet
    fun getAmount(walletId: Int): Double {
        return TransactionsRepository().getTransactions(walletId).map { it.amount }.sum()
    }

    fun getWalletById(id: Int) = wallets.first { it.id == id }

    fun addWallet(wallet: Wallet){
        wallets.add(wallet)
    }
}
