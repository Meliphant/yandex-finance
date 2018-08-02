package ya.co.yandex_finance.ui.fragment.wallets

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallets.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App.Companion.appComponent
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.WalletTypes
import ya.co.yandex_finance.ui.fragment.wallets.adapter.WalletPagerAdapter
import ya.co.yandex_finance.ui.fragment.wallets.adapter.WalletsRecyclerAdapter
import javax.inject.Inject


class WalletsFragment : MvpAppCompatFragment(), WalletsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_wallets, container, false)
        presenter.loadWallets()
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun showWallets(list: ArrayList<Wallet>) {
        //todo remove log
        Log.e("WALLETS", "show wallets: $list")
        val wallets = ArrayList(list)
        wallets.add(0, Wallet(-1, "All wallets", Currency.USD, WalletTypes.CASH))
        wallets.add(list.size, Wallet(-2, "Add wallet", Currency.USD, WalletTypes.CASH))

        rootView.view_pager.adapter = WalletPagerAdapter(wallets, childFragmentManager)
        rootView.recycler_tab_layout.setUpWithAdapter(WalletsRecyclerAdapter(wallets, rootView.view_pager))
    }
}
