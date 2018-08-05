package ya.co.yandex_finance.model.network

import ya.co.yandex_finance.model.entities.DataCurrencyRates

class CurrencyService(private val currencyApi: CurrencyApi) {

    fun loadCurrencies(): DataCurrencyRates {
        return currencyApi.loadCurrencies().blockingFirst()
    }
}
