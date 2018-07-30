package ya.co.yandex_finance.component.fragment.addtransaction


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ya.co.yandex_finance.R
import ya.co.yandex_finance.Util
import ya.co.yandex_finance.repository.model.utils.TransactionType


class AddTransactionFragment : Fragment() {
    private var walletId: Int = -1
    private var transactionType: String = TransactionType.INCOME.name

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
