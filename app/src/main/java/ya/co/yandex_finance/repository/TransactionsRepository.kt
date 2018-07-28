package ya.co.yandex_finance.repository

import ya.co.yandex_finance.repository.model.Transaction
import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.TransactionType
import ya.co.yandex_finance.repository.model.utils.WalletTypes
import java.util.*

class TransactionsRepository {

    private val wallet1 = Wallet("myRubWallet", MyCurrency.RUB, WalletTypes.CASH)
    private val wallet2 = Wallet("myUsdWallet", MyCurrency.USD, WalletTypes.CARD)
    val transactions = mutableListOf<Transaction>()

    init {
        for (i in 1..15)
            transactions.add(Transaction("transaction $i", 200, TransactionType.INCOME, Categories.TRAVEL, wallet1, Date()))
        for (i in 1..15)
            transactions.add(Transaction("another $i", 350, TransactionType.OUTCOME, Categories.RESTAURANTS, wallet2, Date()))
    }
}