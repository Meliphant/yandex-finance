package ya.co.yandex_finance.model.entities

import android.arch.persistence.room.Embedded


class TransactionWithWallet {
    @Embedded
    lateinit var transaction: Transaction

    @Embedded
    lateinit var wallet: Wallet
}