package ya.co.yandex_finance.component

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_settings.*
import ya.co.yandex_finance.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        text_about_app.setOnClickListener{ view ->
            Log.d("LOG", "click")
            Log.d("LOG2", "click")
            print("sok")


            AlertDialog.Builder(this@SettingsActivity).setView(R.layout.dialog_about)
                    .create()
                    .show()
//            startActivity(Intent(this@SettingsActivity, MainActivity::class.java))
        }
    }
}
