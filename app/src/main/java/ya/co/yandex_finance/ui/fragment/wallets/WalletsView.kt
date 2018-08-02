package ya.co.yandex_finance.ui.fragment.wallets

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.repository.model.Wallet

interface WalletsView : MvpView {
    fun showWallets(list: ArrayList<Wallet>)
}
