package ya.co.yandex_finance.component.fragment.transactions

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.repository.TransactionsRepository

@InjectViewState
class TransactionsPresenter: MvpPresenter<TransactionsView>() {

    fun loadTransactions(walletId: Int) {
        //todo: inject by dagger
        //todo: обернуть в асинхронный код
        if (walletId != -1) { //show all wallets case
            viewState.showTransactionsList(TransactionsRepository().getTransactions(walletId))
        } else {
            viewState.showTransactionsList(TransactionsRepository().transactions)
        }
    }
}