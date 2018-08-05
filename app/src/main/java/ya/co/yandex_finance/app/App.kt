package ya.co.yandex_finance.app

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.facebook.stetho.Stetho
import ya.co.yandex_finance.BuildConfig
import ya.co.yandex_finance.app.di.component.AppComponent
import ya.co.yandex_finance.app.di.component.DaggerAppComponent
import ya.co.yandex_finance.app.di.module.AppModule
import ya.co.yandex_finance.model.network.CurrencyWorker
import ya.co.yandex_finance.model.repositories.TransactionsRepository
import ya.co.yandex_finance.model.repositories.WalletsRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class App : Application() {

    companion object {
        const val DATABASE_NAME = "wallet.db"
        const val CURRENCY_PATH = "api/latest.json?app_id=${BuildConfig.CURRENCY_API_KEY}&symbols=RUB,EUR"
        lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var walletsRepository: WalletsRepository
    @Inject
    lateinit var transactionsRepository: TransactionsRepository

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)

        startCurrencyWorker()

        Stetho.initializeWithDefaults(this);
    }

    private fun startCurrencyWorker() {

        WorkManager.getInstance().getStatusesByTag(CurrencyWorker.TAG).observeForever {
            for (work in it!!) {
                if (!work.state.isFinished) {
                    return@observeForever
                }
            }

            val currencyWorker = PeriodicWorkRequest
                    .Builder(CurrencyWorker::class.java, 12, TimeUnit.HOURS)
                    .addTag(CurrencyWorker.TAG)
                    .build()

            WorkManager.getInstance().enqueue(currencyWorker)
        }
    }
}
