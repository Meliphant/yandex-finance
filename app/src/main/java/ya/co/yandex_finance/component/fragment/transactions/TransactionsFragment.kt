package ya.co.yandex_finance.component.fragment.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_transactions_list.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.component.fragment.transactions.adapter.TransactionsAdapter

import ya.co.yandex_finance.repository.model.Transaction

class TransactionsFragment : MvpAppCompatFragment(), TransactionsView {

    @InjectPresenter
    lateinit var presenter: TransactionsPresenter

    override fun showTransactionsList(tr: List<Transaction>) {
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
        presenter.loadTransactions()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Transaction?)
    }
}
