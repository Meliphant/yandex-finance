package ya.co.yandex_finance.model.repositories

import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.Categories
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.WalletTypes
import java.util.*
import kotlin.collections.ArrayList

class TransactionsRepository {

    private val wallet1 = Wallet(0, "myRubWallet", Currency.RUB, WalletTypes.CASH)
    private val wallet2 = Wallet(1, "myUsdWallet", Currency.USD, WalletTypes.CARD)
    private val wallet3 = Wallet(2, "myUsdWallet", Currency.USD, WalletTypes.CARD)

    val transactions = arrayListOf<Transaction>()

    init {
        for (i in 1..15)
            transactions.add(Transaction("got salary $i", 200.0, TransactionType.INCOME, Categories.TRAVEL, wallet1, Date()))
        for (i in 1..15)
            transactions.add(Transaction("dinner outside $i", 350.0, TransactionType.OUTCOME, Categories.RESTAURANTS, wallet2, Date()))
        for (i in 1..15)
            transactions.add(Transaction("bought a bread $i", 50.0, TransactionType.OUTCOME, Categories.GROCERIES, wallet3, Date()))
    }

    fun getTransactions(walletId: Int): ArrayList<Transaction> {
        return ArrayList(transactions.filter { it.wallet.id == walletId })
    }

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
    }
}
