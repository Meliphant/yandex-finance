package ya.co.yandex_finance.ui.fragment.addtransaction

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.model.calculations.BalanceCalculations
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.repositories.TransactionsRecurrentRepository
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import ya.co.yandex_finance.util.applySchedulers
import javax.inject.Inject

@InjectViewState
class AddTransactionPresenter
@Inject constructor(private val transactionsRepository: TransactionsRepository,
                    private val walletsRepository: WalletsRepository,
                    private val transactionsRecurrentRepository: TransactionsRecurrentRepository)
    : MvpPresenter<AddTransactionView>() {

    private val subscription = CompositeDisposable()

    fun addTransaction(transaction: Transaction, wallet: Wallet) {
        transactionsRepository.addTransaction(transaction)
        val balance = BalanceCalculations.sumTransactionWithBalance(wallet.balance, transaction)
        wallet.balance = balance
        walletsRepository.updateWallet(wallet)
    }

    fun loadWallets() {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                walletsRepository.getWallets()
                        .compose(applySchedulers())
                        .subscribe { viewState.showWallets(it) }
        )
    }

    fun addTransactionRecurrent(transactionRecurrent: TransactionRecurrent) {
        transactionsRecurrentRepository.addTransactionRecurrent(transactionRecurrent)
    }
}
