package ya.co.yandex_finance.app.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.model.persistence.WalletDatabase
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import javax.inject.Singleton

@Module
class MainActivityModule {

    @Provides
    @Singleton
    fun provideWalletDatabase(context: Context): WalletDatabase =
            WalletDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideWalletsRepository(walletDatabase: WalletDatabase) =
            WalletsRepository(walletDatabase)

    @Provides
    @Singleton
    fun provideTransactionsRepository(walletDatabase: WalletDatabase) =
            TransactionsRepository(walletDatabase)

}
