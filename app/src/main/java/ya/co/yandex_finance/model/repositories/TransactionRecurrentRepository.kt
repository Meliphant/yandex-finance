package ya.co.yandex_finance.model.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet
import ya.co.yandex_finance.model.persistence.WalletDatabase
import ya.co.yandex_finance.util.applySchedulers

class TransactionsRecurrentRepository(private val walletDatabase: WalletDatabase,
                                      private val subscribeScheduler: Scheduler,
                                      private val observeScheduler: Scheduler) {

    private val subscription = CompositeDisposable()

    fun getTransactionsRecurrentById(trRecId: Int): Flowable<TransactionRecurrent> {
        return walletDatabase.transactionRecurrentDao().getTransactionsRecurrentById(trRecId)
    }

    fun getAllRecurrentTransactions(): Flowable<List<TransactionRecurrent>> {
        return walletDatabase.transactionRecurrentDao().getAllRecurrentTransactions()
    }

    fun getAllRecurrentWithWallet(): Flowable<List<TransactionRecurrentWithWallet>> {
        return walletDatabase.transactionRecurrentDao().getAllRecurrentTransactionsWithWallet()
    }

    fun addTransactionRecurrent(transactionRecurrent: TransactionRecurrent) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction { walletDatabase.transactionRecurrentDao().insert(transactionRecurrent) }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }

    fun deleteTransactionRecurrentById(id: Int) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction { walletDatabase.transactionRecurrentDao().deleteById(id) }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }

    fun updateTransactionRecurrent(transactionRecurrent: TransactionRecurrent) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction {
                    walletDatabase.transactionRecurrentDao().update(transactionRecurrent)
                }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }
}
