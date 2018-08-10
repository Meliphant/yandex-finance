package ya.co.yandex_finance.ui.fragment.transactions

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.app.App.Companion.ALL_WALLETS_ID
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.util.applySchedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class TransactionsPresenter @Inject constructor(private val repository: TransactionsRepository)
    : MvpPresenter<TransactionsView>() {

    private val subscription = CompositeDisposable()

    fun loadTransactions(walletId: Int) {
        if (subscription.isDisposed) subscription.dispose()
        //show all wallets case
        if (walletId != ALL_WALLETS_ID) {
            subscription.add(
                    repository.getTransactionsWithWalletInfoForWallet(walletId)
                            .compose(applySchedulers())
                            .subscribe {
                                viewState.showTransactionsList(it)
                            }
            )
        } else {
            subscription.add(
                    repository.getAllTransactionsWithWalletInfo()
                            .compose(applySchedulers())
                            .subscribe {
                                viewState.showTransactionsList(it)
                            }
            )
        }
    }

    fun filterByDate(dateTimeStart: Date, dateTimeEnd: Date, walletId: Int) {
        if (subscription.isDisposed) subscription.dispose()
        if (walletId != ALL_WALLETS_ID) {
            //show all wallets case
            subscription.add(
                repository.getTransactionsWithPeriodWithWalletInfoFrWallet(dateTimeStart, dateTimeEnd, walletId)
                        .compose(applySchedulers())
                        .subscribe {
                            viewState.showTransactionsList(it)
                        }
            )
        } else {
            subscription.add(
                repository.getTransactionsWithPeriodWithWalletInfo(dateTimeStart, dateTimeEnd)
                        .compose(applySchedulers())
                        .subscribe {
                            viewState.showTransactionsList(it)
                        }
            )
        }
    }
}
