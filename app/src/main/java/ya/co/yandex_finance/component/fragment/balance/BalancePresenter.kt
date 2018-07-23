package ya.co.yandex_finance.component.fragment.balance

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BalancePresenter: MvpPresenter<BalanceView>() {

    fun updateBalance() {
        //todo: call repository return Money model
        //balanceRepository.getBalance(balance -> viewState.setBalance(balance))
        //todo: convertToUsd and make viewState.setBalance(arrayOfCurrencies) ?
        viewState.setBalance(400001.0)
    }

    //todo: Money and map?
    fun convertCurrencyTo(currencyName: String) {
        if(currencyName == "USD_CURR_NAME")
            viewState.setBalance(228.0)
        if (currencyName == "RUB_CURR_NAME")
            viewState.setBalance(40003.1)
    }
}