package ya.co.yandex_finance.model.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CurrencyService(private val currencyApi: CurrencyApi) {

    fun loadCurrencies(currencyRespondResult: CurrencyRespondResult) {

        currencyApi.loadCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { currencyRates ->
                    currencyRespondResult.onCurrencySuccessLoad(currencyRates)
                }
    }
}
