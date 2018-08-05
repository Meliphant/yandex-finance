package ya.co.yandex_finance.model.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.persistence.WalletDatabase

class WalletsRepository(private val walletDatabase: WalletDatabase) {

    //get total money in a wallet
    fun getAmount(walletId: Int): Flowable<List<Transaction>> {
        return walletDatabase.transactionDao().getAllForWallet(walletId)
    }

    fun getWallets(): Flowable<List<Wallet>> {
        return walletDatabase.walletDao().getAllWallets()
    }

    fun getWalletById(id: Int): Single<Wallet> {
        return walletDatabase.walletDao().getById(id)
    }

    fun addWallet(wallet: Wallet) {
        Completable.fromAction { walletDatabase.walletDao().insert(wallet) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun deleteAllWallets() {
        Completable.fromAction { walletDatabase.walletDao().deleteAll() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun deleteWallet(wallet: Wallet) {
        Completable.fromAction { walletDatabase.walletDao().deleteWallet(wallet) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun updateWallet(wallet: Wallet) {
        Completable.fromAction { walletDatabase.walletDao().update(wallet) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}
