package ya.co.yandex_finance.ui.fragment.addwallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.repositories.WalletsRepository
import javax.inject.Inject

@InjectViewState
class AddWalletPresenter
@Inject constructor(private val walletsRepository: WalletsRepository)
    : MvpPresenter<AddWalletView>() {

    fun addWallet(wallet: Wallet) {
        walletsRepository.addWallet(wallet)

    }

    fun showNewWallet(wallets: ArrayList<Wallet>){
        viewState.loadWallet(wallets)
    }
}
