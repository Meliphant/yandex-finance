package ya.co.yandex_finance.model.network

import ya.co.yandex_finance.model.entities.DataCurrencyRates

interface CurrencyRespondResult {

    fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates)
    fun onCurrencyErrorLoad()
}
