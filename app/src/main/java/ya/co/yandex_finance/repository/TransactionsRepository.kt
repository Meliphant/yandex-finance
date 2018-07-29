package ya.co.yandex_finance.repository

import ya.co.yandex_finance.repository.model.Transaction
import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.TransactionType
import ya.co.yandex_finance.repository.model.utils.WalletTypes
import java.util.*
import kotlin.collections.ArrayList

class TransactionsRepository {

    private val wallet1 = Wallet(0, "myRubWallet", MyCurrency.RUB, WalletTypes.CASH)
    private val wallet2 = Wallet(1, "myUsdWallet", MyCurrency.USD, WalletTypes.CARD)
    val transactions = arrayListOf<Transaction>()

    fun getTransactions(walletId: Int): ArrayList<Transaction> {
        return ArrayList(transactions.filter { it.wallet.id ==  walletId })
    }

    init {
        for (i in 1..15)
            transactions.add(Transaction("transaction $i", 200, TransactionType.INCOME, Categories.TRAVEL, wallet1, Date()))
        for (i in 1..15)
            transactions.add(Transaction("another $i", 350, TransactionType.OUTCOME, Categories.RESTAURANTS, wallet2, Date()))
    }
}