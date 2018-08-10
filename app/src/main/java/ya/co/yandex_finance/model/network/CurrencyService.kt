package ya.co.yandex_finance.model.network

import ya.co.yandex_finance.BuildConfig
import ya.co.yandex_finance.model.entities.DataCurrencyRates

class CurrencyService(private val currencyApi: CurrencyApi) {

    fun loadCurrencies(): DataCurrencyRates {
        return currencyApi
                .loadCurrencies(BuildConfig.CURRENCY_API_KEY, BuildConfig.CURRENCY_SYMBOLS)
                .blockingFirst()
    }
}
