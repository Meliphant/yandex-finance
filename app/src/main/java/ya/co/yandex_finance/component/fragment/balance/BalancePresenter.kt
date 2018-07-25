package ya.co.yandex_finance.component.fragment.balance

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ya.co.yandex_finance.component.repository.BalanceRepository

@InjectViewState
class BalancePresenter
(private val balanceRepository: BalanceRepository) : MvpPresenter<BalanceView>() {

    init { }

    //todo: get them myMoney array from repository
    private val usdToRub = 60

    fun convertCurrencyTo(currencyName: String) {
        if (currencyName == "USD_CURR_NAME")
            viewState.setBalance(balanceRepository.getBalance())
        if (currencyName == "RUB_CURR_NAME")
            viewState.setBalance(balanceRepository.getBalance() * usdToRub)

    }


}