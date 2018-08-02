package ya.co.yandex_finance.ui.fragment.wallets.adapter

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nshmura.recyclertablayout.RecyclerTabLayout
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_wallet.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.repository.model.Wallet
import java.util.*

class WalletsRecyclerAdapter(var list: ArrayList<Wallet>, private val myViewPager: ViewPager?)
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

        Glide.with(holder.itemView.context)
                .load(getImage(holder.itemView.context, item.walletType.iconUrl))
                .into(holder.image)

        holder.name.text = item.name

        holder.itemView.wallet_cardView.setBackgroundResource(getTabColor(position))
    }

    private fun getTabColor(position: Int): Int {
        return if (position == currentIndicatorPosition) {
            R.color.tab_background_selected
        } else {
            R.color.tab_background_unselected
        }
    }

    private fun getImage(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: CircleImageView = itemView.wallet_icon
        val name: TextView = itemView.wallet_name

        init {
            itemView.setOnClickListener { viewPager.currentItem = adapterPosition }
        }
    }
}
