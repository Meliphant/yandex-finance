package ya.co.yandex_finance.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.di.appComponent
import ya.co.yandex_finance.model.network.CurrencyRepository
import ya.co.yandex_finance.model.network.CurrencyRespondResult
import ya.co.yandex_finance.model.entities.DataCurrencyRates
import ya.co.yandex_finance.utils.PreferencesManager
import ya.co.yandex_finance.ui.fragment.wallets.WalletsFragment
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var currencyRepository: CurrencyRepository
    private val currencyRespondResult = object: CurrencyRespondResult {
        override fun onCurrencyErrorLoad() {/*NOP*/}
        override fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates) {
            PreferencesManager.setCurrencyRates(this@MainActivity, currencyRates)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        currencyRepository.loadCurrencies(currencyRespondResult)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wallets, WalletsFragment())
                .commitAllowingStateLoss()
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
            android.R.id.home -> { //todo: working ugly because of viewHolder
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_wallets, WalletsFragment())
                        .commitAllowingStateLoss()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startSettings() {
        startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
    }

    //todo remove if use DialogFragment
    fun showUpButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    //todo remove if use DialogFragment
    fun hideUpButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }
}
