package ya.co.yandex_finance.repository

import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.WalletTypes

class WalletsRepository {

    val wallets = arrayListOf<Wallet>()

    init {
        wallets.add(Wallet(0, "myRubWallet", MyCurrency.RUB, WalletTypes.CASH))
        wallets.add(Wallet(1, "myUsdWallet", MyCurrency.USD, WalletTypes.CARD))

        wallets.add(Wallet(2,"anRubWallet", MyCurrency.RUB, WalletTypes.CASH))
        wallets.add(Wallet(3,"anUsdWallet", MyCurrency.USD, WalletTypes.CARD))

        for (i in 15..30)
            wallets.add(Wallet(i,"wl$i", MyCurrency.USD, WalletTypes.CARD))
    }

    //get total money in a wallet
    fun getAmount(walletId: Int): Int {
        return TransactionsRepository().getTransactions(walletId).map { it.amount }.sum()
    }
}