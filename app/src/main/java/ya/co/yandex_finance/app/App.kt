package ya.co.yandex_finance.app

import android.app.Application
import ya.co.yandex_finance.app.di.component.AppComponent
import ya.co.yandex_finance.app.di.component.DaggerAppComponent
import ya.co.yandex_finance.app.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}
