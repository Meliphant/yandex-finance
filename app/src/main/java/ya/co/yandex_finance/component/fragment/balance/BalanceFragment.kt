package ya.co.yandex_finance.component.fragment.balance

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import ya.co.yandex_finance.R
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_balance.*


class BalanceFragment : MvpAppCompatFragment(), BalanceView {

    @InjectPresenter
    lateinit var balancePresenter: BalancePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        balancePresenter.updateBalance()

        return inflater.inflate(R.layout.fragment_balance, container, false)
    }


    override fun setBalance(balance: Int) {
        //todo: make main_currency, sub_currency1 ??
        rub_balance_amount.text = balance.toString()
//        usd_balance_amount.text =
    }
}
