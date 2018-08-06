package ya.co.yandex_finance.model.calculations

import ya.co.yandex_finance.model.entities.*
import ya.co.yandex_finance.model.entities.Currency
import java.util.*

object MockData {
    const val DELTA = 1e-15
    val wallets = ArrayList<Wallet>()
    val transactions = ArrayList<Transaction>()
    val currencyRates = DataCurrencyRates(1L, "USD", mapOf("RUB" to 60.0, "EUR" to 0.8))

    init {
        wallets.add(Wallet(1, "Mock1", 2000.0, Currency.USD, WalletTypes.CARD))
        wallets.add(Wallet(2, "Mock2", 30000.0, Currency.RUB, WalletTypes.CARD))

        transactions.add(Transaction(1, "Mock1", 300.0, TransactionType.INCOME, Categories.CLOTHES, 1, Date()))
        transactions.add(Transaction(2, "Mock2", 500.0, TransactionType.OUTCOME, Categories.CLOTHES, 1, Date()))
    }
}
