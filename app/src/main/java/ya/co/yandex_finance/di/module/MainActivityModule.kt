package ya.co.yandex_finance.di.module

import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.component.fragment.balance.BalancePresenter
import ya.co.yandex_finance.repository.BalanceRepository
import javax.inject.Singleton


@Module
class MainActivityModule {
    //todo: figure out @Scopes
    @Provides @Singleton fun provideRepository() = BalanceRepository()
    @Provides fun provideBalancePresenter(balanceRepository: BalanceRepository) = BalancePresenter(balanceRepository)
}
