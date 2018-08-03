package ya.co.yandex_finance.app.di.component

import dagger.Component
import ya.co.yandex_finance.app.di.module.AppModule
import ya.co.yandex_finance.app.di.module.MainActivityModule
import ya.co.yandex_finance.app.di.module.NetworkModule
import ya.co.yandex_finance.ui.MainActivity
import ya.co.yandex_finance.ui.fragment.addtransaction.AddTransactionDialog
import ya.co.yandex_finance.ui.fragment.addwallets.AddWalletDialog
import ya.co.yandex_finance.ui.fragment.transactions.TransactionsFragment
import ya.co.yandex_finance.ui.fragment.wallets.WalletsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MainActivityModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(walletsFragment: WalletsFragment)

    fun inject(transactionsFragment: TransactionsFragment)

    fun inject(addTransactionDialog: AddTransactionDialog)

    fun inject(addWalletDialog: AddWalletDialog)
}
