package ya.co.yandex_finance.ui.fragment.addtransaction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_add_transaction.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.TransactionType
import ya.co.yandex_finance.ui.fragment.FragmentArguments

class AddTransactionDialog : DialogFragment() {
    private var walletId: Int = -1
    private var transactionType: String = TransactionType.INCOME.name
    val list = WalletsRepository().wallets

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            walletId = it.getInt(FragmentArguments.KEY_WALLET_ID.name)
            transactionType = it.getString(FragmentArguments.KEY_TRANSACTION_TYPE.name)
        }
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

    private fun setupViews() {
        button_close.setOnClickListener { dismiss() }

        button_save?.setOnClickListener { saveTransaction() }

        val walletAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                list.map { it.name })
        walletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallets.adapter = walletAdapter
        spinner_wallets.setSelection(list.indexOf(list.find { it.id == walletId }))

        val categories = Categories.values().map { it.toString() }
        val categoriesAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                categories)
        spinner_category.adapter = categoriesAdapter
        spinner_category.setSelection(0)
    }

    private fun saveTransaction() {
        //todo save transaction

//        val amount = tr_amount.text.toString().toInt()
//        val name = name.text.toString()
//        val category = spinner_category.selectedItemPosition
//
//        val intent = Intent()
//        intent.putExtra(NAME_EXTRA, name)
//        intent.putExtra(AMOUNT_EXTRA, amount)
//        intent.putExtra(TRANSACTION_TYPE_EXTRA, transactionType)
//        intent.putExtra(CATEGORY_TYPE_EXTRA, category)
//
//        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)

        dismiss()
    }

    companion object {
        const val TAG = "AddTransactionDialog"
        const val NAME_EXTRA = "description_extra"
        const val AMOUNT_EXTRA = "amount_extra"
        const val TRANSACTION_TYPE_EXTRA = "transaction_type"
        const val CATEGORY_TYPE_EXTRA = "category_type"

        fun newInstance(walletId: Int, transactionType: TransactionType): DialogFragment =
                AddTransactionDialog().apply {
                    arguments = Bundle().apply {
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                        putString(FragmentArguments.KEY_TRANSACTION_TYPE.name, transactionType.name)
                    }
                }
    }
}
