package ya.co.yandex_finance.ui.fragment.addtransaction

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.Categories
import ya.co.yandex_finance.model.entities.TransactionType
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
    private lateinit var wallet: Wallet
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
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TransactionDialog)
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
        appComponent.inject(this)
    }

    override fun showWallets(list: ArrayList<Wallet>) {
        walletList = list
        val walletAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                walletList.map { it.name })
        walletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallets.adapter = walletAdapter
        spinner_wallets.setSelection(walletList.indexOf(walletList.find { it.id == walletId }))
    }

    private fun setupViews() {
        tv_cancel.setOnClickListener { dismiss() }

        tv_save?.setOnClickListener { onSaveClicked() }

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
        this.wallet = wallet
        saveTransaction()
    }

    private fun saveTransaction() {
        val amount = tr_amount.text.toString().toDouble()
        val name = name.text.toString()
        val categoryId = spinner_category.selectedItemPosition
        val category = Categories.values()[categoryId]

        val transaction = Transaction(name, amount, transactionType, category, wallet, Date())
        presenter.addTransaction(transaction)
        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, Intent())

        dismiss()
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
