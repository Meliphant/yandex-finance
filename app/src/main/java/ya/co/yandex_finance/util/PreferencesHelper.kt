package ya.co.yandex_finance.util

import android.content.Context
import android.preference.PreferenceManager
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.DataCurrencyRates
import java.util.*

const val PREF_CURRENCY_USD_RUB = "pref_currency_usd_rub"
const val PREF_CURRENCY_USD_EUR = "pref_currency_usd_eur"
const val PREF_CURRENCY_TIME = "pref_currency_update_timestamp"

object PreferencesManager {

    fun setCurrencyRates(context: Context, dataCurrencyRates: DataCurrencyRates) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()

        val currencyUsdRub = dataCurrencyRates.rates["RUB"]
        val currencyUsdEur = dataCurrencyRates.rates["EUR"]
        editor.putString(PREF_CURRENCY_USD_RUB, currencyUsdRub.toString())
        editor.putString(PREF_CURRENCY_USD_EUR, currencyUsdEur.toString())
        editor.putLong(PREF_CURRENCY_TIME, dataCurrencyRates.timestamp)
        editor.apply()
    }

    fun getCurrencyRates(context: Context): DataCurrencyRates {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val defaultUsdRub = context.getString(R.string.pref_default_usd_rub_currency_rate)
        val defaultUsdEur = context.getString(R.string.pref_default_usd_rub_currency_rate)
        val currencyUsdRub = sp.getString(PREF_CURRENCY_USD_RUB, defaultUsdRub).toDouble()
        val currencyUsdEur = sp.getString(PREF_CURRENCY_USD_RUB, defaultUsdEur).toDouble()
        val timestamp = sp.getLong(PREF_CURRENCY_TIME, Date().time)

        return DataCurrencyRates(timestamp, "USD",
                mapOf("RUB" to currencyUsdRub, "EUR" to currencyUsdEur))
    }
}
