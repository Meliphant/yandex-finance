package ya.co.yandex_finance.model.calculations

import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.TransactionType

object BalanceCalculations {

    //todo
    /** Пересчет валюты происходит для total суммы, так как каждое новое добаленное поле
    будет сразу единовременно конвертироваться в дефолтную валюту - рубли и храниться в рублях.
     */
    fun countBalanceForRub(transactions: List<Transaction>, currencyRate: Double): Double {
        val totalBalance = getTotalBalance(transactions, currencyRate)
        return getDefaultCurrency(totalBalance, currencyRate)
    }

    fun countBalanceForUsd(transactions: List<Transaction>, currencyRate: Double): Double {
        return getTotalBalance(transactions, currencyRate)
    }

    //todo
//    fun calculateBalance(transactions: List<Transaction>, currencyRate: Double):
//            Pair<Double, Double> {
//
//        val baseBalance = transactions.map {
//            if (it.wallet.currency == Currency.RUB)
//                itemAmountWithSign(it) / currencyRate
//            else
//                itemAmountWithSign(it)
//        }.sum()
//
//        return Pair(baseBalance, baseBalance * currencyRate)
//    }

    //todo
    private fun getTotalBalance(transactions: List<Transaction>,
                                currencyRate: Double): Double {
        var totalBalance = 0.00
        val x = transactions
        val y = currencyRate
        x + y
//        for (transactionItem in transactions) {
//            totalBalance += if (transactionItem.wallet.currency == Currency.RUB) {
//                itemAmountWithSign(transactionItem) / currencyRate
//            } else {
//                itemAmountWithSign(transactionItem)
//            }
//        }
        return totalBalance
    }

    //todo Maybe save Minus Amount (i.e. -500$) From UI?
    private fun itemAmountWithSign(transactionItem: Transaction): Double {
        return if (transactionItem.type == TransactionType.INCOME)
            transactionItem.amount
        else -transactionItem.amount
    }

    private fun getDefaultCurrency(totalBalance: Double, currencyRate: Double): Double {
        return totalBalance * currencyRate
    }
}
