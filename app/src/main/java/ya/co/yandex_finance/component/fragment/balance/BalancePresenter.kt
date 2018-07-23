package ya.co.yandex_finance.component.fragment.balance

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BalancePresenter: MvpPresenter<BalanceView>() {

    //todo: get them from repository
    private val balanceInUsd: Double = 228.02
    private val usdToRub = 60

    //todo: Money and map?
    fun convertCurrencyTo(currencyName: String) {
        if(currencyName == "USD_CURR_NAME")
            viewState.setBalance(balanceInUsd)
        if (currencyName == "RUB_CURR_NAME")
            viewState.setBalance(balanceInUsd * usdToRub)
    }
}