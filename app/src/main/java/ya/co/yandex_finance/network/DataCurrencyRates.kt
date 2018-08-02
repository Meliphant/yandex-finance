package ya.co.yandex_finance.network

data class DataCurrencyRates(val timestamp: Long,
                             val base: String,
                             val rates: Map<String, Double>)