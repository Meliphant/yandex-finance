package ya.co.yandex_finance

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BalancePresenter: MvpPresenter<BalanceView>() {

    fun updateBalance() {
        //todo: call repository return Money model
        //balanceRepository.getBalance(balance -> viewState.setBalance(balance))
        //todo: convertToUsd and make viewState.setBalance(arrayOfCurrencies) ?
        viewState.setBalance(40001)
    }

    //todo: Money and map?
    fun convertTo(mainCurrAmount: Int, rate: Int): Int {
        return mainCurrAmount * rate
    }
}