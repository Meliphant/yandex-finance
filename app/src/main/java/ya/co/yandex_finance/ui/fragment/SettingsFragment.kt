package ya.co.yandex_finance.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.util.Log
import ya.co.yandex_finance.R
import ya.co.yandex_finance.ui.fragment.addtransaction.AddTransactionDialog
import ya.co.yandex_finance.ui.fragment.addwallets.AddWalletDialog

mport android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.ui.fragment.addtransaction.AddTransactionDialog
import ya.co.yandex_finance.ui.fragment.addwallets.AddWalletDialog

class SettingsFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)

        val listPreferenceCurrency: Preference = findPreference(getString(R.string.settings_currency_key))
        val listPreferenceWalletsEdit: Preference = findPreference(getString(R.string.settings_wallets_key_edit))

        setListPreferenceCurrency(listPreferenceCurrency)
        setListPreferenceWallets(listPreferenceWalletsEdit)

        val preferenceWalletsCreate = findPreference(getString(R.string.settings_wallets_key_create))
        preferenceWalletsCreate.setOnPreferenceClickListener {
            navigateAddWalletDialog(this)
            true
        }
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

    //todo: add wallets
    private fun navigateAddWalletDialog(settingsFragment: SettingsFragment) {
        val dialog = AddWalletDialog.newInstance(settingsFragment)
        val ft = fragmentManager?.beginTransaction()
        dialog.show(ft, AddTransactionDialog.TAG)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        if (key == getString(R.string.settings_currency_key)) {
            val connectionPref = findPreference(key)
            //todo: remove log
            Log.d("SettingsActivity", "sharedPref" + connectionPref)
        }

        if (key == getString(R.string.settings_wallets_key_edit)) {
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
