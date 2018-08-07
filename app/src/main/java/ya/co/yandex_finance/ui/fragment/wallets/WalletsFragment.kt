package ya.co.yandex_finance.ui.fragment.wallets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallets.*
import kotlinx.android.synthetic.main.fragment_wallets.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.model.calculations.BalanceCalculations
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.addtransaction.AddTransactionDialog
import ya.co.yandex_finance.ui.fragment.wallets.adapter.WalletPagerAdapter
import ya.co.yandex_finance.ui.fragment.wallets.adapter.WalletsRecyclerAdapter
import ya.co.yandex_finance.util.PreferencesHelper
import javax.inject.Inject

class WalletsFragment : MvpAppCompatFragment(), WalletsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var rootView: View
    private lateinit var walletsAdapeter: WalletsRecyclerAdapter

    private var listener: WalletsFragment.OnListFragmentInteractionListener? = object
        : WalletsFragment.OnListFragmentInteractionListener {
        override fun onListFragmentInteraction(item: Wallet?) {
            Toast.makeText(activity, "$item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_wallets, container, false)
        setupViews(rootView)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.loadWallets()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun showWallets(wallets: List<Wallet>) {
        val customCurrency = PreferencesHelper.getCustomCurrency(context!!)
        val currencyRates = PreferencesHelper.getCurrencyRates(context!!)
        val customBalance = BalanceCalculations
                .convertWalletsBalance(wallets[0], currencyRates, customCurrency)

        walletsAdapeter = WalletsRecyclerAdapter(wallets, customBalance, customCurrency,
                rootView.view_pager, listener)
        rootView.view_pager.adapter = WalletPagerAdapter(wallets, childFragmentManager)
        rootView.recycler_tab_layout.setUpWithAdapter(walletsAdapeter)

    }

    private fun setupViews(rootView: View) {

        rootView.fab_new_income.setOnClickListener {
            openNewTransactionDialog(TransactionType.INCOME)
        }

        rootView.fab_new_expense.setOnClickListener {
            openNewTransactionDialog(TransactionType.OUTCOME)
        }
    }

    private fun openNewTransactionDialog(type: TransactionType) {
        fab_menu.collapse()
        val walletId = walletsAdapeter.getCurrentWalletId()
        val dialog = AddTransactionDialog.newInstance(walletId, type)
        val ft = fragmentManager?.beginTransaction()
        dialog.show(ft, AddTransactionDialog.TAG)
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Wallet?)
    }
}
