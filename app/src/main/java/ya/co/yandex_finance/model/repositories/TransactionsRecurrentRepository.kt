package ya.co.yandex_finance.model.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionWithWallet
import ya.co.yandex_finance.model.persistence.WalletDatabase

class TransactionsRecurrentRepository(private val walletDatabase: WalletDatabase) {

    fun getAllTransactionsWithWallet(): Flowable<List<TransactionWithWallet>> {
        return walletDatabase.transactionDao().getAllTransactionsWithWallet()
    }

    fun getTransactionsWithWallet(walletId: Int): Flowable<List<TransactionWithWallet>> {
        return walletDatabase.transactionDao().getTransactionsWithWallet(walletId)
    }

    fun addTransaction(transaction: Transaction) {
        Completable.fromAction { walletDatabase.transactionDao().insert(transaction) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun deleteAllTransactions() {
        Completable.fromAction { walletDatabase.transactionDao().deleteAll() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun deleteTransactionById(id: Int) {
        Completable.fromAction { walletDatabase.transactionDao().deleteById(id) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun updateTransaction(transaction: Transaction) {
        Completable.fromAction { walletDatabase.transactionDao().update(transaction) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}