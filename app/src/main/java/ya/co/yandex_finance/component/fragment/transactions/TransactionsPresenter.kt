package ya.co.yandex_finance.component.fragment.transactions

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.repository.TransactionsRepository

@InjectViewState
class TransactionsPresenter: MvpPresenter<TransactionsView>() {

    fun loadTransactions(walletId: Int) {
        //todo: inject by dagger
        //todo: обернуть в асинхронный код
        viewState.showTransactionsList(TransactionsRepository().getTransactions(walletId))
    }
}