package ya.co.yandex_finance.ui.fragment.transactions

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import javax.inject.Inject

@InjectViewState
class TransactionsPresenter
@Inject constructor(private val transactionsRepository: TransactionsRepository)
    : MvpPresenter<TransactionsView>() {

    fun loadTransactions(walletId: Int) {
        //todo: обернуть в асинхронный код
        if (walletId != -1) { //show all wallets case
            viewState.showTransactionsList(transactionsRepository.getTransactions(walletId))
        } else {
            viewState.showTransactionsList(transactionsRepository.transactions)
        }
    }
}
