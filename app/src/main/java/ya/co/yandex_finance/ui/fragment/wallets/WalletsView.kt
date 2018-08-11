package ya.co.yandex_finance.ui.fragment.wallets

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.model.entities.Wallet

interface WalletsView : MvpView {
    fun showWallets(list: List<Wallet>, position: Int)
}
