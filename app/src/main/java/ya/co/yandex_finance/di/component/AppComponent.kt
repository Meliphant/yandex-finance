package ya.co.yandex_finance.di.component

import dagger.Component
import ya.co.yandex_finance.di.App
import ya.co.yandex_finance.di.module.AppModule
import ya.co.yandex_finance.di.module.MainActivityModule
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class, MainActivityModule::class))
interface AppComponent {
    fun inject(app: App)
}