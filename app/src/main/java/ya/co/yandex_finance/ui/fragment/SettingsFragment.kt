package ya.co.yandex_finance.ui.fragment

import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.ui.fragment.addwallets.AddWalletDialog
import ya.co.yandex_finance.ui.fragment.editwallet.EditWalletDialog

class SettingsFragment : PreferenceFragmentCompat() {

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



    companion object {
        const val defaultValue = "1"
    }
}
