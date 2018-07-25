package ya.co.yandex_finance.component.di

import android.app.Application
import ya.co.yandex_finance.component.di.component.DaggerAppComponent
import ya.co.yandex_finance.component.di.component.AppComponent
import ya.co.yandex_finance.component.di.module.AppModule

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
