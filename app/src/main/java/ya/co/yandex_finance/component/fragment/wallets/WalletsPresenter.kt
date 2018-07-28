package ya.co.yandex_finance.component.fragment.wallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.repository.WalletsRepository

@InjectViewState //todo: can dagger inject to private fields?
class WalletsPresenter(private val walletsRepository: WalletsRepository): MvpPresenter<WalletsView>() {

    fun loadWallets() {
        viewState.showWallets(walletsRepository.wallets)
    }
}