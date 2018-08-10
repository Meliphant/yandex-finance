package ya.co.yandex_finance.model

import androidx.work.Worker
import io.reactivex.schedulers.Schedulers
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.model.calculations.BalanceCalculations
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.repositories.TransactionsRecurrentRepository
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TransactionRecurrentWorker : Worker() {
    @Inject
    lateinit var transactionsRecurrentRepository: TransactionsRecurrentRepository

    @Inject
    lateinit var transactionsRepository: TransactionsRepository

    @Inject
    lateinit var walletsRepository: WalletsRepository

    override fun doWork(): Worker.Result {
        App.appComponent.inject(this)

        transactionsRecurrentRepository.getAllRecurrentTransactions()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val filtered = it.filter {
                        val updateTime = it.lastupdate.time + TimeUnit.DAYS.toMillis(it.period.toLong())
                        updateTime <= System.currentTimeMillis()
                    }
                    executeOperations(filtered)
                }, {})

        return Worker.Result.SUCCESS
    }

    private fun executeOperations(transactions: List<TransactionRecurrent>) {

        for (transactionRec in transactions) {
            val updateDate = getUpdateDate(transactionRec)

            transactionRec.lastupdate = Date(updateDate)
            transactionsRecurrentRepository.updateTransactionRecurrent(transactionRec)

            val transaction = convertRecurrentToTransaction(transactionRec)
            transactionsRepository.addTransaction(transaction)

            val wallet = walletsRepository.getWalletById(transaction.walletId).blockingGet()
            val balance = BalanceCalculations.sumTransactionWithBalance(wallet.balance, transaction)
            wallet.balance = balance
            walletsRepository.updateWallet(wallet)
        }
    }

    private fun getUpdateDate(transactionRec: TransactionRecurrent): Long {
        return transactionRec.lastupdate.time + TimeUnit.DAYS.toMillis(transactionRec.period.toLong())
    }

    private fun convertRecurrentToTransaction(transactionRecurrent: TransactionRecurrent): Transaction {
        return Transaction(0,
                transactionRecurrent.description,
                transactionRecurrent.amount,
                transactionRecurrent.type,
                transactionRecurrent.category,
                transactionRecurrent.walletId,
                transactionRecurrent.lastupdate)
    }

    companion object {
        const val TAG = "TransactionRecurrentWorker"
    }
}