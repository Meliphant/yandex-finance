package ya.co.yandex_finance.app.di.module

import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.repository.TransactionsRepository
import ya.co.yandex_finance.repository.WalletsRepository

@Module
class MainActivityModule {

    //todo add Scope
    @Provides
    fun provideWalletsRepository() = WalletsRepository()

    //todo add Scope
    @Provides
    fun provideTransactionsRepository() = TransactionsRepository()
}
