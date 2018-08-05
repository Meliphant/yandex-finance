package ya.co.yandex_finance.ui.fragment.addtransaction

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import javax.inject.Inject

@InjectViewState
class AddTransactionPresenter
@Inject constructor(private val transactionsRepository: TransactionsRepository,
                    private val walletsRepository: WalletsRepository)
    : MvpPresenter<AddTransactionView>() {

    fun addTransaction(transaction: Transaction) {
        transactionsRepository.addTransaction(transaction)
    }

    fun loadWallets() {
        walletsRepository.getWallets()
                .subscribe { viewState.showWallets(it) }
    }
}
