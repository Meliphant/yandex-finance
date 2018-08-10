package ya.co.yandex_finance.model.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.entities.TransactionWithWallet
import ya.co.yandex_finance.model.persistence.WalletDatabase
import ya.co.yandex_finance.util.applySchedulers
import java.util.*


class TransactionsRepository(private val walletDatabase: WalletDatabase,
                             private val subscribeScheduler: Scheduler,
                             private val observeScheduler: Scheduler) {

    private val subscription = CompositeDisposable()

    fun getAllTransactionsWithWalletInfo(): Flowable<List<TransactionWithWallet>> {
        return walletDatabase.transactionDao().getAllTransactionsWithWalletInfo()
    }

    fun getTransactionsWithWalletInfoForWallet(walletId: Int): Flowable<List<TransactionWithWallet>> {
        return walletDatabase.transactionDao().getTransactionsWithWalletInfoForWallet(walletId)
    }

    fun addTransaction(transaction: Transaction) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction { walletDatabase.transactionDao().insert(transaction) }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }

    fun getTransactionsWithPeriodWithWalletInfo(dateTimeStart: Date, dateTimeEnd: Date): Flowable<List<TransactionWithWallet>> {
        return walletDatabase.transactionDao().getTransactionsWithPeriodWithWalletInfo(dateTimeStart, dateTimeEnd)
    }

    fun getTransactionsWithPeriodWithWalletInfoFrWallet(dateTimeStart: Date, dateTimeEnd: Date, walletId: Int): Flowable<List<TransactionWithWallet>> {
        return walletDatabase.transactionDao().getTransactionsWithPeriodWithWalletInfoFrWallet(dateTimeStart, dateTimeEnd, walletId)
    }
}
