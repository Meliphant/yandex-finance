package ya.co.yandex_finance.ui.fragment.addtransaction

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.model.entities.Wallet

interface AddTransactionView : MvpView {
    fun showWallet(wallet: Wallet)

    fun showWallets(list: ArrayList<Wallet>)
}
