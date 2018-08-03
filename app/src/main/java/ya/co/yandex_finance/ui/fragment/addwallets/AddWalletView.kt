package ya.co.yandex_finance.ui.fragment.addwallets

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.model.entities.Wallet

interface AddWalletView : MvpView {
    fun showWallet(wallet: Wallet)
}
