package ya.co.yandex_finance.component.fragment.transactions

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_transactions_list.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.Util
import ya.co.yandex_finance.component.fragment.transactions.adapter.TransactionsAdapter
import ya.co.yandex_finance.repository.model.Transaction
import ya.co.yandex_finance.component.fragment.addtransaction.AddTransactionFragment
import ya.co.yandex_finance.repository.model.utils.TransactionType


class TransactionsFragment : MvpAppCompatFragment(), TransactionsView {

    @InjectPresenter
    lateinit var presenter: TransactionsPresenter

    private var walletId = 0

    override fun showTransactionsList(tr: ArrayList<Transaction>) {
        list.adapter = TransactionsAdapter(tr, listener)
    }

    private var listener: OnListFragmentInteractionListener? = object : OnListFragmentInteractionListener{
        override fun onListFragmentInteraction(item: Transaction?) {
            Toast.makeText(activity, "$item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            walletId = it.getInt(Util.KEY_WALLET_ID.name)
        }
        presenter.loadTransactions(walletId)

        //show or hide fab
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 20 && fab_menu.visibility == View.VISIBLE) {
                    fab_menu.collapse()
                    fab_menu.visibility = View.INVISIBLE
                    return
                }
                if (dy < -20 && fab_menu.visibility != View.VISIBLE) {
                    fab_menu.visibility = View.VISIBLE
                    return
                }
            }
        })

        fab_new_income.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_wallets, AddTransactionFragment.newInstance(walletId, TransactionType.INCOME))
                        .commit()
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Transaction?)
    }

    companion object {
        @JvmStatic
        fun newInstance(walletId: Int): TransactionsFragment =  TransactionsFragment().apply {
            arguments = Bundle().apply { putInt(Util.KEY_WALLET_ID.name, walletId) }
        }
    }
}
