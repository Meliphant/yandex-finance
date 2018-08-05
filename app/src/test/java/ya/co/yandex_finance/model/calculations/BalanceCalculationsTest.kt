package ya.co.yandex_finance.model.calculations

import org.junit.Assert.*
import org.junit.Test

class BalanceCalculationsTest {

    @Test
    fun sumTransactionWithBalance() {
        val sum1 = BalanceCalculations.sumTransactionWithBalance(2000.0, MockData.transactions[0])
        assertEquals(2300.0, sum1, MockData.DELTA)

        val sum2 = BalanceCalculations.sumTransactionWithBalance(2000.0, MockData.transactions[1])
        assertEquals(1500.0, sum2, MockData.DELTA)
    }

    @Test
    fun sumWalletsBalance() {
        val sum = BalanceCalculations.sumWalletsBalance(MockData.wallets, MockData.currencyRates)
        assertEquals(2500.0, sum, MockData.DELTA)
    }

    @Test
    fun convertWalletsBalance() {
        val balance = BalanceCalculations
                .convertWalletsBalance(MockData.wallets[0], MockData.currencyRates, "EUR")

        assertEquals(1600.0, balance, MockData.DELTA)
    }
}
