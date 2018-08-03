package ya.co.yandex_finance.ui.fragment.addwallets

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.dialog_add_wallet.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.di.appComponent
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.WalletTypes
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import javax.inject.Inject

class AddWalletDialog : MvpAppCompatDialogFragment(), AddWalletView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AddWalletPresenter

    @ProvidePresenter
    fun provideAddWalletPresenter() = presenter

    private var walletId: Int = -1
    private var transactionType = TransactionType.INCOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TransactionDialog)
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
        super.onAttach(context)
        appComponent.inject(this)
    }

    private fun setupViews() {

        val currencies = Currency.values().map { it.toString() }
        val currencyAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                currencies)
        spinner_currency.adapter = currencyAdapter
        spinner_currency.setSelection(0)

        val walletType = WalletTypes.values().map { it.toString() }
        val walletTypeAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                walletType)
        spinner_wallet_type.adapter = walletTypeAdapter
        spinner_wallet_type.setSelection(0)

        val walletName = et_wallet_name.text.toString().trim()

        val categorySelected = Currency.values()[spinner_currency.selectedItemPosition]
        val walletTypeSelected = WalletTypes.values()[spinner_wallet_type.selectedItemPosition]

        tv_cancel.setOnClickListener { dismiss() }
        tv_save.setOnClickListener {
            if (!walletName.isEmpty()) {
                onSaveClicked(walletName, categorySelected, walletTypeSelected)
            } else {
                showWalletNameError(view!!)
            }
        }
    }

    private fun onSaveClicked(walletName: String, currency: Currency, wallet_type: WalletTypes) {
        //todo: wallet id
        val wallet = Wallet(-1, walletName, currency, wallet_type)
        presenter.addWallet(wallet)
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        view?.postDelayed({ fragmentManager?.popBackStackImmediate() }, 300)
    }

    override fun loadWallet(wallets: ArrayList<Wallet>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showWalletNameError(view: View) {
        Snackbar.make(view, R.string.empty_name_message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "AddWalletDialog"

        fun newInstance(): DialogFragment =
                AddWalletDialog().apply {
                    arguments = Bundle().apply {
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                        putInt(FragmentArguments.KEY_TRANSACTION_TYPE.name, transactionType.ordinal)
                    }
                }
    }
}
