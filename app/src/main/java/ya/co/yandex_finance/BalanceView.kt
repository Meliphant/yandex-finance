package ya.co.yandex_finance

import com.arellomobile.mvp.MvpView

interface BalanceView: MvpView {
    fun setBalance(balance: Int)
}