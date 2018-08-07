package ya.co.yandex_finance.ui.fragment.wallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ya.co.yandex_finance.model.calculations.BalanceCalculations
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.DataCurrencyRates
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.WalletTypes
import ya.co.yandex_finance.model.repositories.WalletsRepository
import javax.inject.Inject

@InjectViewState
class WalletsPresenter
@Inject constructor(private val walletsRepository: WalletsRepository,
                    private val currencyRates: DataCurrencyRates)
    : MvpPresenter<WalletsView>() {

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
        walletsRepository.getWallets()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { showWallets(it) }
    }

    private fun showWallets(wallets: List<Wallet>) {
        val allWallets = ArrayList(wallets)
        val balance = BalanceCalculations.sumWalletsBalance(wallets, currencyRates)
        allWallets.add(0, Wallet(-1, "All wallets", balance, Currency.USD, WalletTypes.CASH))
        viewState.showWallets(allWallets)
    }
}
