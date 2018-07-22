package ya.co.yandex_finance.component

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ya.co.yandex_finance.R
import ya.co.yandex_finance.component.fragment.AboutFragment
import ya.co.yandex_finance.component.fragment.SettingsFragment
import ya.co.yandex_finance.component.fragment.balance.BalanceFragment

class MainActivity: MvpAppCompatActivity() {
    
    //todo: to all of this in the PRESENTER -- use Cicerone?
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main, BalanceFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main, AboutFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main, SettingsFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //by default showing balance
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, BalanceFragment())
                .commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}