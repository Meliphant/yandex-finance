package ya.co.yandex_finance.model.entities

import android.arch.persistence.room.Embedded


class TransactionRecurrentWithWallet {
    @Embedded
    lateinit var transactionRecurrent: TransactionRecurrent

    @Embedded
    lateinit var wallet: Wallet
}
