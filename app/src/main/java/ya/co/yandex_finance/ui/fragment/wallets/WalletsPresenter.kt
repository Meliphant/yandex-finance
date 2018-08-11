package ya.co.yandex_finance.ui.fragment.wallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ya.co.yandex_finance.app.App.Companion.ALL_WALLETS_ID
import ya.co.yandex_finance.app.App.Companion.ALL_WALLETS_NAME
import ya.co.yandex_finance.model.calculations.BalanceCalculations
import ya.co.yandex_finance.model.entities.*
import ya.co.yandex_finance.model.repositories.WalletsRepository
import ya.co.yandex_finance.util.applySchedulers
import javax.inject.Inject

@InjectViewState
class WalletsPresenter
@Inject constructor(private val walletsRepository: WalletsRepository,
                    private val currencyRates: DataCurrencyRates)
    : MvpPresenter<WalletsView>() {

    private val subscription = CompositeDisposable()

    var walletPosition = 0

    fun setPosition(position: Int) {
        walletPosition = position
    }

    fun updateWallet(wallet: Wallet) {
        walletsRepository.updateWallet(wallet)
    }

    fun deleteWallet(wallet: Wallet) {
        walletsRepository.deleteWallet(wallet)
    }

    fun addWallet(wallet: Wallet) {
        walletsRepository.addWallet(wallet)
    }

    fun loadWallets() {
        if (subscription.isDisposed) subscription.dispose()

        subscription.add(
                walletsRepository.getWallets()
                        .compose(applySchedulers())
                        .subscribe { showWallets(it) }
        )
    }

    private fun showWallets(wallets: List<Wallet>) {
        // Update All Wallets balance
        val allWallets = ArrayList(wallets)
        val balance = BalanceCalculations.sumWalletsBalance(wallets, currencyRates)
        allWallets.add(0, Wallet(ALL_WALLETS_ID, ALL_WALLETS_NAME, balance, Currency.USD, WalletTypes.CASH))
        viewState.showWallets(allWallets, walletPosition)
    }
}
