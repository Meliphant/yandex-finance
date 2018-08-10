package ya.co.yandex_finance.ui.fragment.addwallet

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.dialog_add_wallet.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App.Companion.appComponent
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.WalletTypes
import ya.co.yandex_finance.ui.fragment.wallets.WalletsPresenter
import ya.co.yandex_finance.ui.fragment.wallets.WalletsView
import javax.inject.Inject

class AddWalletDialog : MvpAppCompatDialogFragment(), WalletsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var walletId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    override fun onAttach(context: Context?) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    private fun setupViews() {

        val currencies = Currency.values().map { it.toString() }
        setUpAdapter(currencies, spinner_currency)

        val walletType = WalletTypes.values().map { it.toString() }
        setUpAdapter(walletType,spinner_wallet_type)

        tv_cancel.setOnClickListener { dismiss() }

        tv_save.setOnClickListener {
            val walletName = et_wallet_name.text.toString().trim()
            if (!walletName.isEmpty()) {
                val currencySelected = Currency.values()[spinner_currency.selectedItemPosition]
                val walletTypeSelected = WalletTypes.values()[spinner_wallet_type.selectedItemPosition]
                onSaveClicked(walletName, currencySelected, walletTypeSelected)
            } else {
                showWalletNameError(view!!)
            }
        }
    }

    private fun setUpAdapter(spinnerList: List<String>, spinner: Spinner){
        val spinnerAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerList)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(0)
    }

    override fun showWallets(wallets: List<Wallet>, position: Int) {
    }

    private fun onSaveClicked(walletName: String, currency: Currency, wallet_type: WalletTypes) {
        val wallet = Wallet(walletId, walletName, 0.0, currency, wallet_type)
        presenter.addWallet(wallet)

        dismiss()
    }

    private fun showWalletNameError(view: View) {
        Snackbar.make(view, R.string.empty_name_message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "AddWalletDialog"
    }
}
