package ya.co.yandex_finance.repository

import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.Currency
import ya.co.yandex_finance.repository.model.utils.WalletTypes

class WalletsRepository {

    val wallets = arrayListOf<Wallet>()

    init {
        wallets.add(Wallet(0, "myRubWallet", Currency.RUB, WalletTypes.CASH))
        wallets.add(Wallet(1, "myUsdWallet", Currency.USD, WalletTypes.CARD))

        wallets.add(Wallet(2, "anRubWallet", Currency.RUB, WalletTypes.CASH))
        wallets.add(Wallet(3, "anUsdWallet", Currency.USD, WalletTypes.CARD))

        for (i in 15..20)
            wallets.add(Wallet(i, "wl$i", Currency.USD, WalletTypes.CARD))
    }

    //get total money in a wallet
    fun getAmount(walletId: Int): Double {
        return TransactionsRepository().getTransactions(walletId).map { it.amount }.sum()
    }

    fun getWalletById(id: Int) = wallets.first { it.id == id }
}
