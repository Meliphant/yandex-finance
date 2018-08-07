package ya.co.yandex_finance.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.ui.fragment.addwallet.AddWalletDialog
import ya.co.yandex_finance.ui.fragment.editwallet.EditWalletDialog

class SettingsFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val listPreferenceCurrency: Preference = findPreference(getString(R.string.settings_currency_key))
        setListPreferenceCurrency(listPreferenceCurrency)

        val listPreferenceWalletsEdit: Preference = findPreference(getString(R.string.settings_wallets_key_edit))
        listPreferenceWalletsEdit.setOnPreferenceClickListener {
            navigateEditWalletDialog()
            true
        }

        val preferenceWalletsCreate = findPreference(getString(R.string.settings_wallets_key_create))
        preferenceWalletsCreate.setOnPreferenceClickListener {
            navigateAddWalletDialog()
            true
        }
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

    private fun navigateEditWalletDialog() {
        val dialog = EditWalletDialog()
        val ft = fragmentManager?.beginTransaction()
        dialog.show(ft, EditWalletDialog.TAG)
    }

    private fun navigateAddWalletDialog() {
        val dialog = AddWalletDialog()
        val ft = fragmentManager?.beginTransaction()
        dialog.show(ft, AddWalletDialog.TAG)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
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
    }
}
