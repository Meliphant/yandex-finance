package ya.co.yandex_finance.model.entities

data class DataCurrencyRates(val timestamp: Long,
                             val base: String,
                             val rates: Map<String, Double>)
