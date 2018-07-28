package ya.co.yandex_finance.repository

import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.WalletTypes

class WalletsRepository {

    val wallets = arrayListOf<Wallet>()

    init {
        wallets.add(Wallet("myRubWallet", MyCurrency.RUB, WalletTypes.CASH))
        wallets.add(Wallet("myUsdWallet", MyCurrency.USD, WalletTypes.CARD))

        wallets.add(Wallet("anRubWallet", MyCurrency.RUB, WalletTypes.CASH))
        wallets.add(Wallet("anUsdWallet", MyCurrency.USD, WalletTypes.CARD))
    }
}