package ya.co.yandex_finance.ui.fragment.addtransaction

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import ya.co.yandex_finance.model.entities.Transaction
import javax.inject.Inject

@InjectViewState
class AddTransactionPresenter
@Inject constructor(private val transactionsRepository: TransactionsRepository,
                    private val walletsRepository: WalletsRepository)
    : MvpPresenter<AddTransactionView>() {

    fun addTransaction(transaction: Transaction) {
        transactionsRepository.addTransaction(transaction)
    }

    fun loadWalletById(id: Int) {
        viewState.showWallet(walletsRepository.getWalletById(id))
    }

    fun loadWallets() {
        viewState.showWallets(walletsRepository.wallets)
    }
}