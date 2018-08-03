package ya.co.yandex_finance.ui.fragment.addwallets

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceActivity
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.dialog_add_transaction.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.di.appComponent
import ya.co.yandex_finance.model.entities.Categories
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import ya.co.yandex_finance.ui.fragment.SettingsFragment
import javax.inject.Inject

class AddWalletDialog : MvpAppCompatDialogFragment(), AddWalletView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AddWalletPresenter

    @ProvidePresenter
    fun provideAddWalletPresenter() = presenter

    private var walletId: Int = -1
    private lateinit var wallet: Wallet
    private lateinit var walletList: List<Wallet>
    private var transactionType = TransactionType.INCOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            walletId = it.getInt(FragmentArguments.KEY_WALLET_ID.name)
        }

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
        button_close.setOnClickListener { dismiss() }

        button_save?.setOnClickListener { onSaveClicked() }

        val categories = Categories.values().map { it.toString() }
        val categoriesAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                categories)
        spinner_category.adapter = categoriesAdapter
        spinner_category.setSelection(0)
    }

    private fun onSaveClicked() {
        val wallet = spinner_wallets.selectedItemPosition
        presenter.loadWalletById(wallet)
    }

    override fun showWallet(wallet: Wallet) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG = "AddWalletDialog"

        fun newInstance(settingsFragment : SettingsFragment): DialogFragment =
                AddWalletDialog().apply {
                    arguments = Bundle().apply {
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                        putInt(FragmentArguments.KEY_TRANSACTION_TYPE.name, transactionType.ordinal)
                    }
                }
    }
}
