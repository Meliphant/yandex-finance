package ya.co.yandex_finance.component

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.arellomobile.mvp.MvpAppCompatActivity
import ya.co.yandex_finance.R
import ya.co.yandex_finance.component.fragment.balance.BalanceFragment
import android.view.MenuItem


class MainActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //by default showing balance
        //todo: to all of this in the PRESENTER -- use Cicerone?
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, BalanceFragment())
                .commit()
    }

    /**working with menu */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
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