package ya.co.yandex_finance.ui.fragment.editwallet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.edit_wallet.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.Wallet
import ya.co.yandex_finance.model.entities.WalletTypes
import ya.co.yandex_finance.ui.fragment.FragmentArguments
import ya.co.yandex_finance.ui.fragment.wallets.WalletsPresenter
import ya.co.yandex_finance.ui.fragment.wallets.WalletsView
import javax.inject.Inject

class EditWalletDialog : MvpAppCompatDialogFragment(), WalletsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var wallets: ArrayList<Wallet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadWallets()
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    private fun setupViews() {

        tv_cancel_edit.setOnClickListener { dismiss() }
        tv_save_edit.setOnClickListener {

            val wallet = wallets.first { it.name == spinner_wallets_edit.selectedItem.toString()}
            val walletName = et_wallet_name.text.toString()
            val walletStatus = cb_delete.isChecked

            if (walletName.isEmpty() && !walletStatus) {
                showWalletNameError(view!!)
            } else if (walletStatus) {
                et_wallet_name.setText(R.string.about_title)
                onDelete(wallet)
            } else {
                onUpdate(wallet, walletName)
            }
        }
    }

    override fun showWallets(list: List<Wallet>) {
        wallets = ArrayList(list)
        wallets.removeAt(0)
        val walletAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                wallets.map { it.name })
        walletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallets_edit.adapter = walletAdapter
        spinner_wallets_edit.setSelection(0)
    }

    private fun onUpdate(wallet: Wallet, name: String) {
        wallet.name = name
        presenter.updateWallet(wallet)

        dismiss()
    }

    private fun onDelete(wallet: Wallet) {
        presenter.deleteWallet(wallet)

        dismiss()
    }

    private fun showWalletNameError(view: View) {
        Snackbar.make(view, R.string.empty_fields_message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "EditWalletDialog"
    }
}