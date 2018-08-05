package ya.co.yandex_finance.ui.fragment.wallets.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.transactions.TransactionsFragment

class WalletPagerAdapter(private var list: List<Wallet>, fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return TransactionsFragment.newInstance(list[position].wId)
    }

    override fun getCount() = list.size
}
