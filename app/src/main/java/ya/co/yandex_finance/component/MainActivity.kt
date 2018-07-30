package ya.co.yandex_finance.component

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.arellomobile.mvp.MvpAppCompatActivity
import ya.co.yandex_finance.R
import android.view.MenuItem
import ya.co.yandex_finance.component.fragment.wallets.WalletsFragment


class MainActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo: to all of this in the PRESENTER -- use Cicerone?
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wallets, WalletsFragment())
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
            android.R.id.home -> { //todo: working ugly because of viewHolder
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_wallets, WalletsFragment())
                            .commitAllowingStateLoss()
                    return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun startSettings() {
        startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
    }

    fun showUpButton() { supportActionBar!!.setDisplayHomeAsUpEnabled(true) }

    fun hideUpButton() { supportActionBar!!.setDisplayHomeAsUpEnabled(false) }

}