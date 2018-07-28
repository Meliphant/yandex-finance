package ya.co.yandex_finance.component.fragment.wallets.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import ya.co.yandex_finance.R
import ya.co.yandex_finance.repository.model.Wallet
import java.util.ArrayList
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_wallet.view.*


class WalletsAdapter(val list: ArrayList<Wallet>) : RecyclerView.Adapter<WalletsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
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

        holder.name.text = list[position].name
    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var image: CircleImageView = itemView.wallet_icon
        internal var name: TextView = itemView.wallet_name

    }
}