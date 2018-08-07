package ya.co.yandex_finance.util

import android.content.Context
import android.preference.PreferenceManager
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.DataCurrencyRates
import java.util.*

object PreferencesHelper {

    fun setCurrencyRates(context: Context, dataCurrencyRates: DataCurrencyRates) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()

        val currencyUsdRub = dataCurrencyRates.rates["RUB"]
        val currencyUsdEur = dataCurrencyRates.rates["EUR"]
        val currencyUsdRubKey = context.getString(R.string.pref_currency_usd_rub_key)
        val currencyUsdEurKey = context.getString(R.string.pref_currency_usd_eur_key)
        val currencyTimeStamp = context.getString(R.string.pref_currency_timestamp_key)

        editor.putString(currencyUsdRubKey, currencyUsdRub.toString())
        editor.putString(currencyUsdEurKey, currencyUsdEur.toString())
        editor.putLong(currencyTimeStamp, dataCurrencyRates.timestamp)
        editor.apply()
    }

    fun getCurrencyRates(context: Context): DataCurrencyRates {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val currencyUsdRubKey = context.getString(R.string.pref_currency_usd_rub_key)
        val currencyUsdEurKey = context.getString(R.string.pref_currency_usd_eur_key)
        val currencyTimeStamp = context.getString(R.string.pref_currency_timestamp_key)
        val defaultUsdRub = context.getString(R.string.pref_currency_usd_rub_key_default)
        val defaultUsdEur = context.getString(R.string.pref_currency_usd_eur_key_default)
        val currencyUsdRub = sp.getString(currencyUsdRubKey, defaultUsdRub).toDouble()
        val currencyUsdEur = sp.getString(currencyUsdEurKey, defaultUsdEur).toDouble()
        val timestamp = sp.getLong(currencyTimeStamp, Date().time)

        return DataCurrencyRates(timestamp, "USD",
                mapOf("RUB" to currencyUsdRub, "EUR" to currencyUsdEur))
    }

    fun getCustomCurrency(context: Context): String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val defValue = context.getString(R.string.settings_currency_default)
        return sp.getString(context.getString(R.string.settings_currency_key), defValue)
    }
}
