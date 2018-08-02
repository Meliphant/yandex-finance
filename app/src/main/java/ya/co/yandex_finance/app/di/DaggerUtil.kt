package ya.co.yandex_finance.app.di

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import ya.co.yandex_finance.app.App
import ya.co.yandex_finance.app.di.component.AppComponent

val AppCompatActivity.appComponent: AppComponent
    get() = App.appComponent

val FragmentActivity.appComponent: AppComponent
    get() = App.appComponent

val DialogFragment.appComponent: AppComponent
    get() = App.appComponent
