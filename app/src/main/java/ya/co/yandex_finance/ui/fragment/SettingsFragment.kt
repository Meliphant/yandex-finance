package ya.co.yandex_finance.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.util.Log
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Currency

class SettingsFragment : PreferenceFragment(),
        SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)

        val listPreferenceCurrency: Preference = findPreference(getString(R.string.settings_currency_key))
        val listPreferenceWallets: Preference = findPreference(getString(R.string.settings_wallets_key))
        setListPreferenceCurrency(listPreferenceCurrency)
        setListPreferenceWallets(listPreferenceWallets)
    }

    private fun setListPreferenceCurrency(listPreference: Preference) {

        if (listPreference is ListPreference) {

            //TODO: Here should be probably loaded wider list
            val entries = arrayOf<CharSequence>(Currency.USD.toString(),
                    Currency.RUB.toString(), Currency.EUR.toString())
            val currencyKeySharedPref = arrayOf<CharSequence>(Currency.USD.toString(),
                    Currency.RUB.toString(), Currency.EUR.toString())
            listPreference.entries = entries
            listPreference.setDefaultValue(defaultValue)
            listPreference.entryValues = currencyKeySharedPref
            listPreference.setDialogTitle(R.string.settings_currency_title)
        }
    }

    private fun setListPreferenceWallets(listPreference: Preference) {

        if (listPreference is ListPreference) {
            //TODO: Load real wallets
            val entries = arrayOf<CharSequence>(Currency.USD.toString(),
                    Currency.RUB.toString(), Currency.EUR.toString())
            val walletsKeySharedPref = arrayOf<CharSequence>(Currency.USD.toString(),
                    Currency.RUB.toString(), Currency.EUR.toString())
            listPreference.entries = entries
            listPreference.setDefaultValue(defaultValue)
            listPreference.entryValues = walletsKeySharedPref
            listPreference.setDialogTitle(R.string.settings_wallets_title)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        if (key == getString(R.string.settings_currency_key)) {
            val connectionPref = findPreference(key)
            //todo: remove log
            Log.d("SettingsActivity", "sharedPref" + connectionPref)
        }

        if (key == getString(R.string.settings_wallets_key)) {
            //todo: add wallets editor
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
        const val defaultValue = "1"
    }
}
