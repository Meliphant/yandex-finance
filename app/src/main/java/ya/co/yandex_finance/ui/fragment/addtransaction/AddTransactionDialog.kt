package ya.co.yandex_finance.ui.fragment.addtransaction

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.model.entities.Categories
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import java.util.*
import javax.inject.Inject

class AddTransactionDialog : MvpAppCompatDialogFragment(), AddTransactionView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AddTransactionPresenter

    @ProvidePresenter
    fun provideAddTransactionPresenter() = presenter

    private var walletId: Int = -1
    private lateinit var walletList: List<Wallet>
    private var transactionType = TransactionType.INCOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            walletId = it.getInt(FragmentArguments.KEY_WALLET_ID.name)
            transactionType = TransactionType
                    .values()[it.getInt(FragmentArguments.KEY_TRANSACTION_TYPE.name)]
        }
        presenter.loadWallets()
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun showWallets(list: List<Wallet>) {
        walletList = list
        val walletAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                walletList.map { it.name })
        walletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallets_edit.adapter = walletAdapter
        spinner_wallets_edit.setSelection(walletList.indexOf(walletList.find { it.wId == walletId }))
    }

    private fun setupViews() {

        val categories = Categories.values().map { it.toString() }
        val categoriesAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                categories)
        spinner_category.adapter = categoriesAdapter
        spinner_category.setSelection(0)

        tv_cancel.setOnClickListener { dismiss() }

        tv_save?.setOnClickListener { saveTransaction() }
    }

    private fun saveTransaction() {

        val name = name.text.toString()
        val categoryId = spinner_category.selectedItemPosition
        val category = Categories.values()[categoryId]
        val amount = tr_amount.text.toString().trim()
        val walletName = spinner_wallets_edit.selectedItem.toString()
        val wallet = walletList.first { it.name == walletName}

        if (!amount.isEmpty()) {
            val transaction = Transaction(0, name, amount.toDouble(), transactionType, category, wallet.wId, Date())
            presenter.addTransaction(transaction, wallet)

            dismiss()
        } else {
            showError(view!!)
        }
    }

    private fun showError(view: View) {
        Snackbar.make(view, R.string.empty_amount_message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "AddTransactionDialog"

        fun newInstance(walletId: Int, transactionType: TransactionType): DialogFragment =
                AddTransactionDialog().apply {
                    arguments = Bundle().apply {
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                        putInt(FragmentArguments.KEY_TRANSACTION_TYPE.name, transactionType.ordinal)
                    }
                }
    }
}
