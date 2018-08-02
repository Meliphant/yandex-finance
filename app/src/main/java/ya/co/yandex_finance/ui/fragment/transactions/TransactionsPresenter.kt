package ya.co.yandex_finance.ui.fragment.transactions

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.repository.TransactionsRepository
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.Transaction
import ya.co.yandex_finance.repository.model.Wallet
import javax.inject.Inject

@InjectViewState
class TransactionsPresenter
@Inject constructor(private val transactionsRepository: TransactionsRepository,
                    private val walletsRepository: WalletsRepository)
    : MvpPresenter<TransactionsView>() {

    fun loadTransactions(walletId: Int) {
        //todo: обернуть в асинхронный код
        if (walletId != -1) { //show all wallets case
            viewState.showTransactionsList(transactionsRepository.getTransactions(walletId))
        } else {
            viewState.showTransactionsList(transactionsRepository.transactions)
        }
    }

    fun addTransaction(transaction: Transaction) {
        transactionsRepository.addTransaction(transaction)
    }

    fun getWalletById(id: Int): Wallet {
        return walletsRepository.getWalletById(id)
    }
}
