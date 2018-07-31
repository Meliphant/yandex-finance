package ya.co.yandex_finance.component.fragment.wallets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallets.*
import kotlinx.android.synthetic.main.fragment_wallets.view.*

import ya.co.yandex_finance.R
import ya.co.yandex_finance.component.fragment.wallets.adapter.WalletPagerAdapter
import ya.co.yandex_finance.component.fragment.wallets.adapter.WalletsRecyclerAdapter
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.WalletTypes


class WalletsFragment : MvpAppCompatFragment(), WalletsView {

    @InjectPresenter lateinit var presenter: WalletsPresenter
    @ProvidePresenter
    fun provideWalletsPresenter() = WalletsPresenter(WalletsRepository()) //todo: inject by DAGGER

    lateinit var v:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_wallets, container, false)
        presenter.loadWallets()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showWallets(list: ArrayList<Wallet>) {
        Log.e("WALLETS", "show wallets: $list")
        list.add(0, Wallet(-1, "All wallets", MyCurrency.USD, WalletTypes.CASH))
        list.add(list.size, Wallet(-2, "Add wallet", MyCurrency.USD, WalletTypes.CASH))
        v.view_pager.adapter = WalletPagerAdapter(list, childFragmentManager)
        v.recycler_tab_layout.setUpWithAdapter(WalletsRecyclerAdapter(list, v.view_pager))
    }

}
