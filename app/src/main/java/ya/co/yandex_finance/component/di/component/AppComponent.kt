package ya.co.yandex_finance.component.di.component

import dagger.Component
import ya.co.yandex_finance.component.di.App
import ya.co.yandex_finance.component.di.module.AppModule
import ya.co.yandex_finance.component.di.module.MainActivityModule
import ya.co.yandex_finance.component.fragment.balance.BalanceFragment
import ya.co.yandex_finance.component.fragment.balance.BalancePresenter
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class, MainActivityModule::class))
interface AppComponent {
    fun inject(app: App)

    fun inject(balanceFragment: BalanceFragment)
    fun inject(balancePresenter: BalancePresenter)
}