package ya.co.yandex_finance.component

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.arellomobile.mvp.MvpAppCompatActivity
import ya.co.yandex_finance.R
import ya.co.yandex_finance.component.fragment.balance.BalanceFragment
import android.view.MenuItem
import ya.co.yandex_finance.component.fragment.transactions.TransactionsFragment
import ya.co.yandex_finance.component.fragment.wallets.WalletsFragment


class MainActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo: to all of this in the PRESENTER -- use Cicerone?
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wallets, WalletsFragment())
                .commitAllowingStateLoss()

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, TransactionsFragment())
                .commitAllowingStateLoss()
    }

    /**working with menu */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_settings -> {
                startSettings()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun startSettings() {
        startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
    }


}