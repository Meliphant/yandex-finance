package ya.co.yandex_finance.component.fragment.transactions.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ya.co.yandex_finance.R


import ya.co.yandex_finance.component.fragment.transactions.TransactionsFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.item_transaction.view.*
import ya.co.yandex_finance.repository.model.Transaction
import ya.co.yandex_finance.repository.model.utils.TransactionType
import java.text.SimpleDateFormat

class TransactionsAdapter(
        private val mValues: List<Transaction>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Transaction
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.trName.text = item.name
        holder.trDateTime.text = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(item.dateTime)

        Glide.with(holder.mView.context)
                .load(getImage(holder.mView.context, item.category.iconUrl))
                .into(holder.trCategoryImg)

        if (item.type == TransactionType.INCOME) {
            holder.trAmount.text = "+ ${item.amount.toString()} ${item.wallet.currency.sign}"
            holder.trAmount.setTextColor(Color.GREEN)
        }
        if (item.type == TransactionType.OUTCOME) {
            holder.trAmount.text = "- ${item.amount.toString()} ${item.wallet.currency.sign}"
            holder.trAmount.setTextColor(Color.RED)
        }

        Glide.with(holder.mView.context)
                .load(getImage(holder.mView.context, item.wallet.walletType.iconUrl))
                .into(holder.trWalletImg)

        holder.trWalletName.text = item.wallet.name
    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val trDateTime: TextView = mView.tr_datetime
        val trName: TextView = mView.tr_name
        val trCategoryImg: ImageView = mView.tr_category_img
        val trWalletImg: ImageView = mView.wallet_img
        val trWalletName: TextView = mView.wallet_name
        val trAmount: TextView = mView.tr_amount
    }
}
