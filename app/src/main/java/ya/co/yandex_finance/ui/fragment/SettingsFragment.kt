package ya.co.yandex_finance.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.util.PreferencesHelper
import java.text.SimpleDateFormat
import java.util.*

class SettingsFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val listPreferenceCurrency: Preference = findPreference(getString(R.string.settings_currency_key))
        setListPreferenceCurrency(listPreferenceCurrency)

        setCurrencyLastUpdate()
    }

    private fun setCurrencyLastUpdate() {
        val currencyLastUpdateView: Preference = findPreference(getString(R.string.settings_currency_last_update_key))
        val currencyRates = PreferencesHelper.getCurrencyRates(activity!!)
        val lastUpdate = currencyFormat.format(Date(currencyRates.timestamp))
        currencyLastUpdateView.title = getString(R.string.settings_currency_last_update, lastUpdate)
    }

    private fun setListPreferenceCurrency(listPreference: Preference) {

        if (listPreference is ListPreference) {

            val entries = Currency.values().map { it.toString() }.toTypedArray()
            listPreference.entries = entries
            listPreference.setDefaultValue(DEFAULT_VALUE)
            listPreference.entryValues = entries
            listPreference.setDialogTitle(R.string.settings_currency_title)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == getString(R.string.settings_currency_key)) {
            val connectionPref = findPreference(key)
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(this)
    }

    companion object {
        const val DEFAULT_VALUE = "1"
        private val currencyFormat = SimpleDateFormat("d MMM", Locale.getDefault())
    }
}
