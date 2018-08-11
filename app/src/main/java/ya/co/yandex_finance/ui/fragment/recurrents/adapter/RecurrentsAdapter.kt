package ya.co.yandex_finance.ui.fragment.recurrents.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recurrent.view.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.ui.fragment.recurrents.RecurrentsFragment.Companion.DELETE
import ya.co.yandex_finance.ui.fragment.recurrents.RecurrentsFragment.Companion.EDIT
import ya.co.yandex_finance.ui.fragment.recurrents.RecurrentsFragment.OnListFragmentInteractionListener
import java.text.SimpleDateFormat
import java.util.*

class RecurrentsAdapter(
        private val context: Context,
        private val recurrents: List<TransactionRecurrentWithWallet>,
        private val listener: OnListFragmentInteractionListener)
    : RecyclerView.Adapter<RecurrentsAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat(context.getString(R.string.pattern_dateformat), Locale.ROOT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recurrent, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = recurrents[position]

        Glide.with(holder.itemView.context)
                .load(getImage(holder.itemView.context, item.transactionRecurrent.category.iconUrl))
                .into(holder.trCategoryImg)

        Glide.with(holder.itemView.context)
                .load(getImage(holder.itemView.context, item.wallet.walletType.iconUrl))
                .into(holder.trWalletImg)

        holder.trName.text = item.transactionRecurrent.description
        val updateTitle = context.getString(R.string.last_payment)
        holder.trDateTime.text = updateTitle.plus(dateFormat.format(item.transactionRecurrent.dateTime))
        holder.trPeriod.text = holder.itemView.context.getString(R.string.recurrent_period,
                item.transactionRecurrent.period)

        when (item.transactionRecurrent.type) {
            TransactionType.INCOME -> setAmount(ContextCompat.getColor(context,
                    R.color.transaction_text_income), item, holder, R.string.income_amount)
            TransactionType.OUTCOME -> setAmount(ContextCompat.getColor(context,
                    R.color.transaction_text_outcome), item, holder, R.string.outcome_amount)
        }

        holder.trWalletName.text = item.wallet.name

        holder.trDelete.setOnClickListener {
            listener.onListFragmentInteraction(item.transactionRecurrent.trRecId, DELETE, item.wallet.wId)
            Log.d("RecurrentAdapter", "deleted")
        }

        holder.trEdit.setOnClickListener {
            listener.onListFragmentInteraction(item.transactionRecurrent.trRecId, EDIT, item.wallet.wId)
            Log.d("RecurrentAdapter", "edit")
        }
    }

    override fun getItemCount(): Int = recurrents.size

    private fun setAmount(color: Int, item: TransactionRecurrentWithWallet, holder: ViewHolder, format: Int) {
        holder.trAmount.setTextColor(color)
        val sign = holder.itemView.context
                .getString(item.wallet.currency.signResourceId)
        holder.trAmount.text = holder.itemView.context
                .getString(format, item.transactionRecurrent.amount, sign)
    }

    private fun getImage(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, context.getString(R.string.pic_def_type),
                context.packageName)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trDateTime: TextView = itemView.tr_datetime
        val trPeriod: TextView = itemView.tr_period
        val trName: TextView = itemView.tr_name
        val trCategoryImg: ImageView = itemView.tr_category_img
        val trWalletImg: ImageView = itemView.wallet_img
        val trWalletName: TextView = itemView.wallet_name
        val trAmount: TextView = itemView.tr_amount
        val trDelete: ImageView = itemView.tr_delete
        val trEdit: ImageView = itemView.tr_edit
    }
}
