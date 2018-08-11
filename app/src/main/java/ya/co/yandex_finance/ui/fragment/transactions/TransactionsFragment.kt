package ya.co.yandex_finance.ui.fragment.transactions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.twigsntwines.daterangepicker.DateRangePickedListener
import kotlinx.android.synthetic.main.fragment_transactions_list.*
import kotlinx.android.synthetic.main.fragment_transactions_list.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App.Companion.ALL_WALLETS_ID
import ya.co.yandex_finance.app.App.Companion.appComponent
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionWithWallet
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import ya.co.yandex_finance.ui.fragment.transactions.adapter.TransactionsAdapter
import java.util.*
import javax.inject.Inject

class TransactionsFragment : MvpAppCompatFragment(), TransactionsView {

    @Inject
    @InjectPresenter
    lateinit var transactionsPresenter: TransactionsPresenter

    @ProvidePresenter
    fun provideTransactionPresenter() = transactionsPresenter

    private lateinit var rootView: View
    private var walletId = ALL_WALLETS_ID

    private var listener: OnListFragmentInteractionListener? = object
        : OnListFragmentInteractionListener {
        override fun onListFragmentInteraction(item: Transaction?) {
            Toast.makeText(activity, "$item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_transactions_list, container, false)

        arguments?.let {
            walletId = it.getInt(FragmentArguments.KEY_WALLET_ID.name)
        }
        transactionsPresenter.loadTransactions(walletId)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_list_filter.setDateRangePickedListener(object : DateRangePickedListener {
            override fun OnDateRangePicked(fromDate: Calendar, toDate: Calendar) {
                transactionsPresenter.filterByDate(fromDate.time, toDate.time, walletId)
                Log.d("Fragment", "Picker: " + fromDate.time + " " + toDate.time)
            }
        })
    }

    override fun onAttach(context: Context?) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showTransactionsList(transactions: List<TransactionWithWallet>) {
        if (transactions.isEmpty()) {
            showViews(emptyVisibility = View.VISIBLE)
        } else {
            showViews(transactionsVisibility = View.VISIBLE)
            rootView.rv_list_transactions.adapter = TransactionsAdapter(context!!, transactions, listener)
        }
    }

    private fun showViews(emptyVisibility: Int = View.INVISIBLE,
                          transactionsVisibility: Int = View.INVISIBLE) {
        rootView.tv_no_transactions.visibility = emptyVisibility
        rootView.rv_list_transactions.visibility = transactionsVisibility
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Transaction?)
    }

    companion object {
        fun newInstance(walletId: Int): TransactionsFragment =
                TransactionsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                    }
                }
    }
}
