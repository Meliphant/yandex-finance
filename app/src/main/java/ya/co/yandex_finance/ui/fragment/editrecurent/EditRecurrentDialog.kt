package ya.co.yandex_finance.ui.fragment.editrecurent

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.edit_recurrent_template.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.app.App.Companion.ALL_WALLETS_ID
import ya.co.yandex_finance.model.entities.Categories
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import ya.co.yandex_finance.ui.fragment.recurrents.RecurrentPresenter
import ya.co.yandex_finance.ui.fragment.recurrents.RecurrentView
import javax.inject.Inject

class EditRecurrentDialog : MvpAppCompatDialogFragment(), RecurrentView {

    @Inject
    @InjectPresenter
    lateinit var presenterRecurrent: RecurrentPresenter

    @ProvidePresenter
    fun provideRecurrentPresenter() = presenterRecurrent

    private var recurrentID: Int = ALL_WALLETS_ID
    private var walletId: Int = ALL_WALLETS_ID

    private lateinit var walletList: List<Wallet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            walletId = it.getInt(FragmentArguments.KEY_WALLET_ID.name)
            recurrentID = it.getInt(FragmentArguments.KEY_RECURRENT_ID.name)
        }
        presenterRecurrent.loadRecurrentTemplates()
        presenterRecurrent.loadWallets()
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_recurrent_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onAttach(context: Context?) {
        App.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun setupViews() {

        val category = Categories.values().map { it.toString() }
        setUpAdapter(category, spinner_category)
        val descriptionCurrent = presenterRecurrent.getRecurrentTransactionById(recurrentID).description
        val amountCurrent = presenterRecurrent.getRecurrentTransactionById(recurrentID).amount
        val periodCurrent = presenterRecurrent.getRecurrentTransactionById(recurrentID).period
        val categoryCurrent = presenterRecurrent.getRecurrentTransactionById(recurrentID).category

        text_description.setText(descriptionCurrent)
        tr_amount.setText(EditRecurrentDialog.FORMAT_AMOUNT.format(amountCurrent))
        recurrent.setText(periodCurrent.toString())
        spinner_category.setSelection(categoryCurrent.ordinal)

        tv_cancel.setOnClickListener { dismiss() }

        tv_save.setOnClickListener {

            // setup current items
            var templateDescription = text_description.text.toString()
            var wallet = walletList.first { it.name == spinner_wallets_recurrent.selectedItem.toString() }
            var categories = Categories.values()[spinner_category.selectedItemPosition]
            var period = recurrent.text
            var interval = if (!period.isEmpty()) period.toString().trim().toInt() else 0
            val templateAmount = tr_amount.text.toString()
            var amount = if (!templateAmount.isEmpty()) templateAmount.trim().toDouble() else 0.0

            if (!templateDescription.isEmpty() && amount != 0.0 && interval != 0) {
                onUpdate(presenterRecurrent.getRecurrentTransactionById(recurrentID),
                        templateDescription,
                        amount,
                        wallet.wId,
                        categories,
                        interval)
            } else {
                showTemplateError(view!!)
            }
        }
    }

    override fun showWallets(wallets: List<Wallet>) {
        walletList = ArrayList(wallets)

        val walletAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item,
                wallets.map { it.name })
        walletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallets_recurrent.adapter = walletAdapter
        spinner_wallets_recurrent.setSelection(wallets.indexOf(wallets.find {
            it.wId == presenterRecurrent.getRecurrentTransactionById(recurrentID).walletId
        }))
    }

    private fun setUpAdapter(spinnerList: List<String>, spinner: Spinner) {
        val spinnerAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerList)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(0)
    }

    private fun onUpdate(transactionRecurrent: TransactionRecurrent, templateDescription: String,
                         amount: Double, walletId: Int, category: Categories, interval: Int) {
        transactionRecurrent.description = templateDescription
        transactionRecurrent.amount = amount
        transactionRecurrent.walletId = walletId
        transactionRecurrent.category = category
        transactionRecurrent.period = interval
        presenterRecurrent.updateRecurrentTemplates(transactionRecurrent)

        dismiss()
    }

    override fun showRecurrents(recurrents: List<TransactionRecurrentWithWallet>) {
    }

    private fun showTemplateError(view: View) {
        Snackbar.make(view, R.string.recurrent_empty_fields_message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "EditRecurrentDialog"
        const val FORMAT_AMOUNT = "%.1f"

        fun newInstance(recurrentId: Int, walletId: Int): EditRecurrentDialog =
                EditRecurrentDialog().apply {
                    arguments = Bundle().apply {
                        putInt(FragmentArguments.KEY_RECURRENT_ID.name, recurrentId)
                        putInt(FragmentArguments.KEY_WALLET_ID.name, walletId)
                    }
                }
    }
}
