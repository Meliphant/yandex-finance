package ya.co.yandex_finance.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ya.co.yandex_finance.R
import ya.co.yandex_finance.ui.fragment.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        addSettingsList()
    }

    private fun addSettingsList() {
        if (fragmentManager.findFragmentById(android.R.id.content) == null) {
            fragmentManager.beginTransaction()
                    .add(android.R.id.content, SettingsFragment())
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
