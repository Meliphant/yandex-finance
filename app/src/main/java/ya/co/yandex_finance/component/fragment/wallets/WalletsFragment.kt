package ya.co.yandex_finance.component.fragment.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallets.*

import ya.co.yandex_finance.R
import ya.co.yandex_finance.component.fragment.wallets.adapter.WalletPagerAdapter
import ya.co.yandex_finance.component.fragment.wallets.adapter.WalletsRecyclerAdapter
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.Wallet


class WalletsFragment : MvpAppCompatFragment(), WalletsView {

    @InjectPresenter lateinit var presenter: WalletsPresenter
    @ProvidePresenter
    fun provideWalletsPresenter() = WalletsPresenter(WalletsRepository()) //todo: inject by DAGGER

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.loadWallets()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showWallets(list: ArrayList<Wallet>) {
        view_pager.adapter = WalletPagerAdapter(list, activity!!.supportFragmentManager)
        recycler_tab_layout.setUpWithAdapter(WalletsRecyclerAdapter(list, view_pager))
    }

}
