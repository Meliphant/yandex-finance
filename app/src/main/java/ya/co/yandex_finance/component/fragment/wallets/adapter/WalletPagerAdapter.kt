package ya.co.yandex_finance.component.fragment.wallets.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ya.co.yandex_finance.component.fragment.transactions.TransactionsFragment
import ya.co.yandex_finance.repository.model.Wallet

class WalletPagerAdapter(private var list: ArrayList<Wallet>, private val fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return TransactionsFragment.newInstance(list[position].id)
    }

    override fun getCount(): Int {
        return list.size
    }

}