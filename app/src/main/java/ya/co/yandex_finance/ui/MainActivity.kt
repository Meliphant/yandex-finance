package ya.co.yandex_finance.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import ya.co.yandex_finance.R
import ya.co.yandex_finance.ui.fragment.wallets.WalletsFragment

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceWallets()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startSettings()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            android.R.id.home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_wallets, WalletsFragment())
                        .commitAllowingStateLoss()
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

    private fun replaceWallets() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wallets, WalletsFragment())
                .commitAllowingStateLoss()
    }

    private fun startSettings() {
        startActivityForResult(Intent(this@MainActivity, SettingsActivity::class.java), RC_SETTINGS)
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this@MainActivity)
                .setView(R.layout.dialog_about)
                .setTitle(R.string.about_title)
                .setPositiveButton(android.R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }

    companion object {
        const val RC_SETTINGS = 21
    }
}
