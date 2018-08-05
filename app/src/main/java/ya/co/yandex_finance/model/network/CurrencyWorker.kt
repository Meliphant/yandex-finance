package ya.co.yandex_finance.model.network

import androidx.work.Worker
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.model.entities.DataCurrencyRates
import ya.co.yandex_finance.util.PreferencesManager.setCurrencyRates
import ya.co.yandex_finance.util.isInternetAvailable
import javax.inject.Inject


class CurrencyWorker : Worker(), CurrencyRespondResult {

    @Inject
    lateinit var currencyService: CurrencyService

    override fun doWork(): Worker.Result {
        App.appComponent.inject(this)

        if (!isInternetAvailable(applicationContext)) {
            return Worker.Result.FAILURE
        }

        currencyService.loadCurrencies(this)

        return Worker.Result.SUCCESS
    }

    override fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates) {
         setCurrencyRates(applicationContext, currencyRates)
    }

    companion object {
        const val TAG = "CurrencyWorker"
    }
}