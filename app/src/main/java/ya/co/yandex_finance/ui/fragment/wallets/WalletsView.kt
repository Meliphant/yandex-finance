package ya.co.yandex_finance.ui.fragment.wallets

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.model.entities.Wallet

interface WalletsView : MvpView {
    fun showWallets(wallets: List<Wallet>, position: Int)
}
