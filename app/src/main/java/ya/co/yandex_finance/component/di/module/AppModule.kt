package ya.co.yandex_finance.component.di.module


import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.component.di.App
import ya.co.yandex_finance.component.fragment.balance.BalancePresenter
import ya.co.yandex_finance.component.repository.BalanceRepository
import javax.inject.Singleton

@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
}