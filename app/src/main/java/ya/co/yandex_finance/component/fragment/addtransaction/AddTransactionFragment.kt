package ya.co.yandex_finance.component.fragment.addtransaction


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_add_transaction.*

import ya.co.yandex_finance.R
import ya.co.yandex_finance.Util
import ya.co.yandex_finance.component.MainActivity
import ya.co.yandex_finance.component.fragment.wallets.WalletsFragment
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.TransactionType

//todo: make it as DialogFragment?
class AddTransactionFragment : Fragment() {
    private var walletId: Int = -1
    private var transactionType: String = TransactionType.INCOME.name
    val list = WalletsRepository().wallets


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            walletId = it.getInt(Util.KEY_WALLET_ID.name)
            transactionType = it.getString(Util.KEY_TRANSACTION_TYPE.name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity!! as MainActivity).showUpButton()

        val walletAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list.map { it.name })
        walletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallets.adapter = walletAdapter
        spinner_wallets.setSelection(list.indexOf(list.find { it.id == walletId }))

        val categories = Categories.values().map { it.toString() }
        val categoriesAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories)
        spinner_category.adapter = categoriesAdapter
        spinner_wallets.setSelection(0)

        //todo: working ugly because of viewHolder
        btn_add_transaction.setOnClickListener({
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_wallets, WalletsFragment())
                    .commitAllowingStateLoss()
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        (activity!! as MainActivity).hideUpButton()
        super.onDetach()
    }


    companion object {
        @JvmStatic
        fun newInstance(walletId: Int, transactionType: TransactionType): Fragment =
                AddTransactionFragment().apply {
                    arguments = Bundle().apply {
                        putInt(Util.KEY_WALLET_ID.name, walletId)
                        putString(Util.KEY_TRANSACTION_TYPE.name, transactionType.name)
                    }
                }
    }
}
