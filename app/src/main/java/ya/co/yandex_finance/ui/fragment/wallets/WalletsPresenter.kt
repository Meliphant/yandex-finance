package ya.co.yandex_finance.ui.fragment.wallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.Wallet
import javax.inject.Inject

@InjectViewState
class WalletsPresenter @Inject constructor(private val walletsRepository: WalletsRepository)
    : MvpPresenter<WalletsView>() {

    fun loadWallets() {
        viewState.showWallets(walletsRepository.wallets)
    }
}
