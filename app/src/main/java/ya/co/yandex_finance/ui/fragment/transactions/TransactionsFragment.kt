package ya.co.yandex_finance.ui.fragment.transactions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_transactions_list.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App.Companion.appComponent
import ya.co.yandex_finance.repository.model.Transaction
import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.TransactionType
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import ya.co.yandex_finance.ui.fragment.addtransaction.AddTransactionDialog
import ya.co.yandex_finance.ui.fragment.transactions.adapter.TransactionsAdapter
import java.util.*
import javax.inject.Inject


const val FAB_SCROLL_THRESHOLD = 20

class TransactionsFragment : MvpAppCompatFragment(), TransactionsView {

    @Inject
    @InjectPresenter
    lateinit var transactionsPresenter: TransactionsPresenter

    @ProvidePresenter
    fun provideTransactionPresenter() = transactionsPresenter

    private lateinit var rootView: View
    private var walletId = -1

    private var listener: OnListFragmentInteractionListener? = object
        : OnListFragmentInteractionListener {
        override fun onListFragmentInteraction(item: Transaction?) {
            Toast.makeText(activity, "$item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_transactions_list, container, false)

        arguments?.let {
            walletId = it.getInt(FragmentArguments.KEY_WALLET_ID.name)
        }
        transactionsPresenter.loadTransactions(walletId)

        setupViews(rootView)
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showTransactionsList(tr: ArrayList<Transaction>) {
        rootView.rv_list_transactions.adapter = TransactionsAdapter(tr, listener)
    }

    private fun setupViews(rootView: View) {
        //todo Remove?
//        //show or hide fab
//        rootView.rv_list_transactions.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                if (dy > FAB_SCROLL_THRESHOLD && fab_menu.visibility == View.VISIBLE) {
//                    fab_menu.collapse()
//                    fab_menu.visibility = View.INVISIBLE
//                    return
//                }
//                if (dy < -FAB_SCROLL_THRESHOLD && fab_menu.visibility != View.VISIBLE) {
//                    fab_menu.visibility = View.VISIBLE
//                }
//            }
//        })

        rootView.fab_new_income.setOnClickListener {
            val dialog = AddTransactionDialog.newInstance(walletId, TransactionType.INCOME)
            val ft = fragmentManager?.beginTransaction()
            dialog.setTargetFragment(this, DIALOG_REQUEST_CODE)
            dialog.show(ft, AddTransactionDialog.TAG)
        }

        rootView.fab_new_expense.setOnClickListener {
            val dialog = AddTransactionDialog.newInstance(walletId, TransactionType.OUTCOME)
            val ft = fragmentManager?.beginTransaction()
            dialog.setTargetFragment(this, DIALOG_REQUEST_CODE)
            dialog.show(ft, AddTransactionDialog.TAG)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DIALOG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            transactionsPresenter.loadTransactions(walletId)
        }
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Transaction?)
    }

    companion object {
        const val DIALOG_REQUEST_CODE = 42
        fun newInstance(walletId: Int): TransactionsFragment = TransactionsFragment().apply {
            arguments = Bundle().apply { putInt(FragmentArguments.KEY_WALLET_ID.name, walletId) }
        }
    }
}
