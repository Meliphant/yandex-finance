package ya.co.yandex_finance.app.di.module

import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.repository.TransactionsRepository
import ya.co.yandex_finance.repository.WalletsRepository
import javax.inject.Singleton

@Module
class MainActivityModule {

    @Provides
    @Singleton
    fun provideWalletsRepository() = WalletsRepository()

    @Provides
    @Singleton
    fun provideTransactionsRepository() = TransactionsRepository()
}
