package ya.co.yandex_finance.di

import android.app.Application
import ya.co.yandex_finance.di.component.DaggerAppComponent
import ya.co.yandex_finance.di.component.AppComponent
import ya.co.yandex_finance.di.module.AppModule

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
