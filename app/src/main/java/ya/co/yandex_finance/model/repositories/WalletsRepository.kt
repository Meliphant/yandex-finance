package ya.co.yandex_finance.model.repositories


import io.reactivex.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.persistence.WalletDatabase
import ya.co.yandex_finance.util.applySchedulers

class WalletsRepository(private val walletDatabase: WalletDatabase,
                        private val subscribeScheduler: Scheduler,
                        private val observeScheduler: Scheduler) {

    private val subscription = CompositeDisposable()

    fun getWallets(): Flowable<List<Wallet>> {
        return walletDatabase.walletDao().getAllWallets()
    }

    fun getWalletById(id: Int): Single<Wallet> {
        return walletDatabase.walletDao().getById(id)
    }

    fun addWallet(wallet: Wallet) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction { walletDatabase.walletDao().insert(wallet) }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }

    fun deleteWallet(wallet: Wallet) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction { walletDatabase.walletDao().deleteWallet(wallet) }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }

    fun updateWallet(wallet: Wallet) {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                Completable.fromAction { walletDatabase.walletDao().update(wallet) }
                        .compose(applySchedulers(subscribeScheduler, observeScheduler))
                        .subscribe()
        )
    }
}
