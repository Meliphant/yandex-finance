package ya.co.yandex_finance.model.network

import androidx.work.Worker
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.util.PreferencesHelper.setCurrencyRates
import javax.inject.Inject

class CurrencyWorker : Worker() {

    @Inject
    lateinit var currencyService: CurrencyService

    override fun doWork(): Worker.Result {
        App.appComponent.inject(this)

        val currencyRates = currencyService.loadCurrencies()
        setCurrencyRates(applicationContext, currencyRates)

        return Worker.Result.SUCCESS
    }

    companion object {
        const val TAG = "CurrencyWorker"
    }
}
