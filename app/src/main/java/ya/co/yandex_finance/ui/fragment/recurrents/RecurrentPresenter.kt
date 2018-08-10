package ya.co.yandex_finance.ui.fragment.recurrents

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.repositories.TransactionsRecurrentRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import ya.co.yandex_finance.util.applySchedulers
import javax.inject.Inject

@InjectViewState
class RecurrentPresenter
@Inject constructor(private val recurrentRepository: TransactionsRecurrentRepository,
                    private val walletsRepository: WalletsRepository)
    : MvpPresenter<RecurrentView>() {

    private val subscription = CompositeDisposable()

    fun loadRecurrents() {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                recurrentRepository.getAllRecurrentWithWallet()
                        .compose(applySchedulers())
                        .subscribe { viewState.showRecurrents(it) }
        )
    }

    fun deleteRecurrent(recurrentId: Int) {
        recurrentRepository.deleteTransactionRecurrentById(recurrentId)
    }

    fun updateRecurrentTemplates(transactionRecurrent: TransactionRecurrent) {
        recurrentRepository.updateTransactionRecurrent(transactionRecurrent)
    }

    fun getRecurrentTransactionById(trRecId: Int): TransactionRecurrent {
        return recurrentRepository.getTransactionsRecurrentById(trRecId).blockingFirst()
    }

    fun loadRecurrentTemplates() {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                recurrentRepository.getAllRecurrentWithWallet()
                        .compose(applySchedulers())
                        .subscribe { showRecurrentTemplates(it) }
        )
    }

    private fun showRecurrentTemplates(transactionRecurrent: List<TransactionRecurrentWithWallet>) {
        val allTemplates = ArrayList(transactionRecurrent)
        viewState.showRecurrents(allTemplates)
    }

    fun loadWallets() {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                walletsRepository.getWallets()
                        .compose(applySchedulers())
                        .subscribe { showWallets(it) }
        )
    }

    fun getWalletById(id: Int): Wallet {
        return walletsRepository.getWalletById(id).blockingGet()
    }

    private fun showWallets(wallets: List<Wallet>) {
        viewState.showWallets(wallets)
    }
}
