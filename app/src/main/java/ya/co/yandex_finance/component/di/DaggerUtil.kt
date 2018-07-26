package ya.co.yandex_finance.component.di

import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import ya.co.yandex_finance.component.di.component.AppComponent

val AppCompatActivity.component: AppComponent
    get() = (application as App).component

val FragmentActivity.component: AppComponent
    get() = (application as App).component