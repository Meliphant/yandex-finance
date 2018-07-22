package ya.co.yandex_finance.component.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ya.co.yandex_finance.R

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //todo: make it as an activity??
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


}
