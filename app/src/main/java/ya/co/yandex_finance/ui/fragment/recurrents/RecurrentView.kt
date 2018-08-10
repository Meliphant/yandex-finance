package ya.co.yandex_finance.ui.fragment.recurrents

import com.arellomobile.mvp.MvpView
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet
import ya.co.yandex_finance.model.entities.Wallet

interface RecurrentView : MvpView {
    fun showRecurrents(recurrents: List<TransactionRecurrentWithWallet>)
    fun showWallets(wallets: List<Wallet>)
}
