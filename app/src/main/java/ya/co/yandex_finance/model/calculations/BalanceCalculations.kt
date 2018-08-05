package ya.co.yandex_finance.model.calculations

import ya.co.yandex_finance.model.entities.*

object BalanceCalculations {

    fun sumTransactionWithBalance(balance: Double, transaction: Transaction): Double {
        val amount = getAmountWithSign(transaction)
        return balance + amount
    }

    fun sumWalletsBalance(wallets: List<Wallet>, currencyRates: DataCurrencyRates): Double {
        return wallets.map {
            when {
                it.currency == Currency.USD -> it.balance
                else -> it.balance / currencyRates.rates[it.currency.toString()]!!
            }
        }.sum()
    }

    fun convertWalletsBalance(wallet: Wallet, currencyRates: DataCurrencyRates, currency: String): Double {
        return when (currency) {
            wallet.currency.toString() -> wallet.balance
            else -> {
                wallet.balance * currencyRates.rates[currency]!!
            }
        }
    }

    private fun getAmountWithSign(transactionItem: Transaction): Double {
        return if (transactionItem.type == TransactionType.INCOME)
            transactionItem.amount
        else -transactionItem.amount
    }
}
