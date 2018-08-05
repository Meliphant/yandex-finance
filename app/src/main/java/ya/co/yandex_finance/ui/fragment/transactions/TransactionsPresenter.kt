package ya.co.yandex_finance.ui.fragment.transactions

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import javax.inject.Inject

@InjectViewState
class TransactionsPresenter @Inject constructor(private val repository: TransactionsRepository)
    : MvpPresenter<TransactionsView>() {

    fun loadTransactions(walletId: Int) {
        //show all wallets case
        if (walletId != -1) {
            repository.getTransactionsWithWallet(walletId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        viewState.showTransactionsList(it)
                    }
        } else {
            repository.getAllTransactionsWithWallet()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        viewState.showTransactionsList(it)
                    }
        }
    }
}
