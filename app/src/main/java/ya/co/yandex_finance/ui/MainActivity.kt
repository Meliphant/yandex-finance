package ya.co.yandex_finance.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_with_drawer.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.ui.fragment.addwallet.AddWalletDialog
import ya.co.yandex_finance.ui.fragment.editwallet.EditWalletDialog
import ya.co.yandex_finance.ui.fragment.recurrents.RecurrentsFragment
import ya.co.yandex_finance.ui.fragment.wallets.WalletsFragment

class MainActivity : MvpAppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_with_drawer)

        setupWallets()
        actionBarHandler()
        navigationViewHandler()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        actionBarHandler()
    }

    private fun setupWallets() {
        if (supportFragmentManager.findFragmentByTag(WalletsFragment.TAG) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_wallets, WalletsFragment(), WalletsFragment.TAG)
                    .commit()
        }
    }

    private fun actionBarHandler() {
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun navigationViewHandler() {

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        navigationView.setNavigationItemSelectedListener { menu ->
            menu.isChecked = true
            mDrawerLayout.closeDrawers()
            when (menu.itemId) {
                R.id.drawer_settings -> startSettings()
                R.id.drawer_about -> showAboutDialog()
                R.id.drawer_wallets_create -> navigateAddWalletDialog()
                R.id.drawer_wallets_edit -> navigateEditWalletDialog()
                R.id.drawer_template_recurrent_edit -> navigateRecurrentList()
            }
            false
        }
    }

    private fun replaceWallets() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_wallets, WalletsFragment(), WalletsFragment.TAG)
                .commit()
    }

    private fun startSettings() {
        startActivityForResult(Intent(this@MainActivity, SettingsActivity::class.java), RC_SETTINGS)
    }

    private fun showAboutDialog() {
        val dialog = AlertDialog.Builder(this@MainActivity)
                .setView(R.layout.dialog_about)
                .setTitle(R.string.about_title)
                .setPositiveButton(android.R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .create();
        dialog.show()
        doKeepDialog(dialog)
    }

    private fun doKeepDialog(dialog: Dialog) {
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = lp
    }

    private fun navigateEditWalletDialog() {
        if (supportFragmentManager.findFragmentByTag(EditWalletDialog.TAG) == null) {
            val dialog = EditWalletDialog()
            val ft = supportFragmentManager?.beginTransaction()
            dialog.show(ft, EditWalletDialog.TAG)
        }
    }

    private fun navigateAddWalletDialog() {
        if (supportFragmentManager.findFragmentByTag(AddWalletDialog.TAG) == null) {
            val dialog = AddWalletDialog()
            val ft = supportFragmentManager?.beginTransaction()
            dialog.show(ft, AddWalletDialog.TAG)
        }
    }

    private fun navigateRecurrentList() {
        if (supportFragmentManager.findFragmentByTag(RecurrentsFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_wallets, RecurrentsFragment(), RecurrentsFragment.TAG)
                    .addToBackStack(RecurrentsFragment.TAG)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SETTINGS && resultCode == 21) {
            replaceWallets()
        }
    }

    companion object {
        const val RC_SETTINGS = 21
    }
}
