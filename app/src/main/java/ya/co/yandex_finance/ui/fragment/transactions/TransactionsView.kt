package ya.co.yandex_finance.ui.fragment.transactions

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.model.entities.TransactionWithWallet

interface TransactionsView : MvpView {
    fun showTransactionsList(transactions: List<TransactionWithWallet>)
}
