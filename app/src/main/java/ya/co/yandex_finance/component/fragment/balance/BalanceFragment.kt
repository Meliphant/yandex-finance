package ya.co.yandex_finance.component.fragment.balance

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import ya.co.yandex_finance.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_balance.*
import ya.co.yandex_finance.component.di.component
import javax.inject.Inject


class BalanceFragment : MvpAppCompatFragment(), BalanceView, AdapterView.OnItemSelectedListener {

    @Inject
    @InjectPresenter
    lateinit var balancePresenter: BalancePresenter
    @ProvidePresenter fun provideBalancePresenter() = balancePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo: arrayOf myMoney && do it all in the presenter
        val currencyNames = arrayOf("rub", "usd", "eur")
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, currencyNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        current_currency_spinner.adapter = adapter
        current_currency_spinner.onItemSelectedListener = this
        current_currency_spinner.setSelection(1)
    }


    override fun setBalance(balance: Double) {
        current_balance_amount.text = String.format("%.2f", balance)
    }


    override fun onNothingSelected(patent: AdapterView<*>) { }

    override fun onItemSelected(patent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        chooseCurrency(if (pos == 0) "USD_CURR_NAME" else "RUB_CURR_NAME")
    }

    private fun chooseCurrency(currencyName: String) {
        balancePresenter.convertCurrencyTo(currencyName)
    }
}
