package ya.co.yandex_finance.ui.fragment.transactions

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.repository.model.Transaction

interface TransactionsView: MvpView {
    fun showTransactionsList(tr: ArrayList<Transaction>)
}
