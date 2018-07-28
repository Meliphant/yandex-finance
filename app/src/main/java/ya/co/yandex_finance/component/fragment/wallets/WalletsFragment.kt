package ya.co.yandex_finance.component.fragment.wallets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ya.co.yandex_finance.R
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallets.*
import ya.co.yandex_finance.component.fragment.wallets.adapter.WalletsAdapter
import ya.co.yandex_finance.repository.WalletsRepository
import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.WalletTypes
import java.util.*


class WalletsFragment : MvpAppCompatFragment(), WalletsView {

    @InjectPresenter
    lateinit var presenter: WalletsPresenter

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
        //first and last elements are custom
        list.add(list.size, Wallet("add new", MyCurrency.RUB, WalletTypes.CASH))
        list.add(0, Wallet("All wallets", MyCurrency.RUB, WalletTypes.CASH))

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        wallets_recycler.layoutManager = layoutManager
        val adapter = WalletsAdapter(list)
        wallets_recycler.adapter = adapter
    }


}
