package ya.co.yandex_finance.component.fragment.balance

import com.arellomobile.mvp.MvpView

interface BalanceView: MvpView {
    fun setBalance(balance: Double)
}