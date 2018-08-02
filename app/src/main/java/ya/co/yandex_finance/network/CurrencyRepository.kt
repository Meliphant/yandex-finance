package ya.co.yandex_finance.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CurrencyRepository(private val currencyApi: CurrencyApi) {

    private var disposable: CompositeDisposable? = CompositeDisposable()

    fun loadCurrencies(currencyRespondResult: CurrencyRespondResult) {

        if (disposable?.isDisposed == true) {
            disposable?.dispose()
        }

        disposable?.add(
                currencyApi.loadCurrencies()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ currencyRates ->
                            currencyRespondResult.onCurrencySuccessLoad(currencyRates)
                        }, { error ->
                            error.printStackTrace()
                            currencyRespondResult.onCurrencyErrorLoad()
                        })
        )
    }
}
