package ya.co.yandex_finance.ui.fragment.transactions.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_transaction.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.TransactionWithWallet
import ya.co.yandex_finance.ui.fragment.transactions.TransactionsFragment.OnListFragmentInteractionListener
import java.text.SimpleDateFormat
import java.util.*

class TransactionsAdapter(
        private val context: Context,
        private val transactions: List<TransactionWithWallet>,
        private val listener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val dateFormat = SimpleDateFormat("dd.MM HH:mm", Locale.ROOT)
    init {
        mOnClickListener = View.OnClickListener {
            val item = it.tag as Transaction
            listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = transactions[position]

        Glide.with(holder.itemView.context)
                .load(getImage(holder.itemView.context, item.transaction.category.iconUrl))
                .into(holder.trCategoryImg)

        Glide.with(holder.itemView.context)
                .load(getImage(holder.itemView.context, item.wallet.walletType.iconUrl))
                .into(holder.trWalletImg)

        holder.trName.text = item.transaction.description
        holder.trDateTime.text = dateFormat.format(item.transaction.dateTime)

        when (item.transaction.type) {
            TransactionType.INCOME -> setAmount(ContextCompat.getColor(context, R.color.transaction_text_income), item, holder, R.string.income_amount)
            TransactionType.OUTCOME -> setAmount(ContextCompat.getColor(context, R.color.transaction_text_outcome), item, holder, R.string.outcome_amount)

        }

        holder.trWalletName.text = item.wallet.name
    }

    override fun getItemCount(): Int = transactions.size

    private fun setAmount(color: Int, item: TransactionWithWallet, holder: ViewHolder, format: Int) {
        holder.trAmount.setTextColor(color)
        holder.trAmount.text = holder.itemView.context
                .getString(format, item.transaction.amount, item.wallet.currency.sign)
    }

    private fun getImage(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trDateTime: TextView = itemView.tr_datetime
        val trName: TextView = itemView.tr_name
        val trCategoryImg: ImageView = itemView.tr_category_img
        val trWalletImg: ImageView = itemView.wallet_img
        val trWalletName: TextView = itemView.wallet_name
        val trAmount: TextView = itemView.tr_amount
    }
}
