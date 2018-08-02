package ya.co.yandex_finance.ui.fragment.wallets.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ya.co.yandex_finance.repository.model.Wallet
import ya.co.yandex_finance.ui.fragment.transactions.TransactionsFragment

class WalletPagerAdapter(private var list: ArrayList<Wallet>, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return TransactionsFragment.newInstance(list[position].id)
    }

    override fun getCount(): Int {
        return list.size
    }
}
