package ya.co.yandex_finance.app

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.facebook.stetho.Stetho
import ya.co.yandex_finance.R
import ya.co.yandex_finance.app.di.component.AppComponent
import ya.co.yandex_finance.app.di.component.DaggerAppComponent
import ya.co.yandex_finance.app.di.module.AppModule
import ya.co.yandex_finance.model.TransactionRecurrentWorker
import ya.co.yandex_finance.model.network.CurrencyWorker
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        const val ALL_WALLETS_ID = -1
        lateinit var ALL_WALLETS_NAME: String
        private const val CURRENCY_UPDATE_PERIOD: Long = 12
        private const val RECURRENT_UPDATE_PERIOD: Long = 1
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        ALL_WALLETS_NAME = getString(R.string.all_wallets_name)
        startCurrencyWorker()
        startTransactionRecurrentWorker()
        Stetho.initializeWithDefaults(this);
    }

    private fun startCurrencyWorker() {

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

        WorkManager.getInstance()?.getStatusesByTag(CurrencyWorker.TAG)?.observeForever {
            for (work in it!!) {
                if (!work.state.isFinished) {
                    return@observeForever
                }
            }

            val currencyWorker = PeriodicWorkRequest
                    .Builder(CurrencyWorker::class.java,
                            CURRENCY_UPDATE_PERIOD, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .addTag(CurrencyWorker.TAG)
                    .build()

            WorkManager.getInstance()?.enqueue(currencyWorker)
        }
    }

    private fun startTransactionRecurrentWorker() {

        WorkManager.getInstance()?.getStatusesByTag(TransactionRecurrentWorker.TAG)?.observeForever {
            for (work in it!!) {
                if (!work.state.isFinished) {
                    return@observeForever
                }
            }

            val transactionRecurrentWorker = PeriodicWorkRequest
                    .Builder(TransactionRecurrentWorker::class.java,
                            RECURRENT_UPDATE_PERIOD, TimeUnit.DAYS)
                    .addTag(TransactionRecurrentWorker.TAG)
                    .build()

            WorkManager.getInstance()?.enqueue(transactionRecurrentWorker)
        }
    }
}
