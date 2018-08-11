package ya.co.yandex_finance.ui.fragment.wallets.adapter

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.item_wallet.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.wallets.WalletsFragment

class WalletsRecyclerAdapter(private var list: List<Wallet>,
                             private var convertedBalance: Double,
                             private var customCurrency: String,
                             myViewPager: ViewPager?,
                             private val listener: WalletsFragment.OnListFragmentInteractionListener?)
    : RecyclerTabLayout.Adapter<WalletsRecyclerAdapter.ViewHolder>(myViewPager) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wallet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.name.text = item.name
        holder.currency.text = item.currency.toString()
        holder.amount.text = item.balance.toString()
        holder.itemView.wallet_cardView.setBackgroundResource(getTabColor(position))

        if (position == 0) {
            holder.currency.visibility = View.INVISIBLE
            holder.amount.visibility = View.GONE
            holder.customCurrency.text = customCurrency
            holder.customAmount.text = FORMAT_AMOUNT.format(convertedBalance)
            holder.customTitle.visibility = View.VISIBLE
        }
    }

    fun getCurrentWalletId(): Int = list[currentIndicatorPosition].wId

    fun getCount(): Int = list.size

    fun updateData(wallets: List<Wallet>,
                   convertedBalance: Double,
                   customCurrency: String) {
        this.convertedBalance = convertedBalance
        this.customCurrency = customCurrency
        this.list = wallets
        notifyDataSetChanged()
    }

    private fun getTabColor(position: Int): Int {
        return if (position == currentIndicatorPosition) {
            R.color.tab_background_selected
        } else {
            R.color.tab_background_unselected
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.wallet_name
        val currency: TextView = itemView.wallet_currency
        val amount: TextView = itemView.wallet_amount
        val customCurrency: TextView = itemView.custom_currency
        val customAmount: TextView = itemView.custom_amount
        val customTitle: TextView = itemView.tv_custom_title

        init {
            itemView.setOnClickListener {
                viewPager.currentItem = adapterPosition
                listener?.onListFragmentInteraction(adapterPosition)
            }
        }
    }

    companion object {
        const val FORMAT_AMOUNT = "%.1f"
    }
}
