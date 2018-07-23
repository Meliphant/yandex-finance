package ya.co.yandex_finance.component


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import ya.co.yandex_finance.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        text_about_app.setOnClickListener{ view ->
            AlertDialog.Builder(this@SettingsActivity)
                    .setView(R.layout.dialog_about)
                    .create()
                    .show()
        }

        this.title = "Настройки"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId) {
            android.R.id.home -> {
                backHome()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun backHome() {
        //todo: use Cicerone
        this.finish()
    }
}
