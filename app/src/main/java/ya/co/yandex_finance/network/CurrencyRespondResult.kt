package ya.co.yandex_finance.network

interface CurrencyRespondResult {

    fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates)
    fun onCurrencyErrorLoad()
}
