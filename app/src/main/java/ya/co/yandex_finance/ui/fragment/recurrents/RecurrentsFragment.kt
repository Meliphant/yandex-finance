package ya.co.yandex_finance.ui.fragment.recurrents

import android.content.Context
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.*
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_recurrent_list.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.addtransaction.AddTransactionDialog
import ya.co.yandex_finance.ui.fragment.editrecurent.EditRecurrentDialog
import ya.co.yandex_finance.ui.fragment.recurrents.adapter.RecurrentsAdapter
import javax.inject.Inject

class RecurrentsFragment : MvpAppCompatFragment(), RecurrentView {

    @Inject
    @InjectPresenter
    lateinit var presenter: RecurrentPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var rootView: View

    private var listener: RecurrentsFragment.OnListFragmentInteractionListener = object
        : RecurrentsFragment.OnListFragmentInteractionListener {
        override fun onListFragmentInteraction(recurrentId: Int, type: String, walletId: Int) {
            if (type == DELETE) presenter.deleteRecurrent(recurrentId)
            else openEditRecurrentDialog(recurrentId, walletId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_recurrent_list, container, false)
        presenter.loadRecurrents()
        val actionbar: ActionBar? = (activity!! as MvpAppCompatActivity).supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(false)
            title = activity!!.getString(R.string.drawer_template_recurrent_title)
        }
        return rootView
    }

    override fun onAttach(context: Context?) {
        App.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.recurrent, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_close_recurrent) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showRecurrents(recurrents: List<TransactionRecurrentWithWallet>) {
        if (recurrents.isEmpty()) {
            showViews(emptyVisibility = View.VISIBLE)
        } else {
            showViews(recurrentsVisibility = View.VISIBLE)
            rootView.rv_list_recurrent.adapter = RecurrentsAdapter(context!!, recurrents, listener)
        }
    }

    private fun showViews(emptyVisibility: Int = View.INVISIBLE,
                          recurrentsVisibility: Int = View.INVISIBLE) {
        rootView.tv_no_recurrent.visibility = emptyVisibility
        rootView.rv_list_recurrent.visibility = recurrentsVisibility
    }

    private fun openEditRecurrentDialog(recurrentId: Int, walletId: Int) {
        val dialog = EditRecurrentDialog.newInstance(recurrentId, walletId)
        val ft = fragmentManager?.beginTransaction()
        dialog.show(ft, EditRecurrentDialog.TAG)
    }

    override fun showWallets(wallets: List<Wallet>) {
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(recurrentId: Int, type: String, walletId: Int)
    }

    companion object {
        const val TAG = "RecurrentsFragment"
        const val DELETE = "DELETE"
        const val EDIT = "EDIT"
    }
}