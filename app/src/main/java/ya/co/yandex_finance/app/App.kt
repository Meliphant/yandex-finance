package ya.co.yandex_finance.app

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.facebook.stetho.Stetho
import ya.co.yandex_finance.BuildConfig
import ya.co.yandex_finance.app.di.component.AppComponent
import ya.co.yandex_finance.app.di.component.DaggerAppComponent
import ya.co.yandex_finance.app.di.module.AppModule
import ya.co.yandex_finance.model.network.CurrencyWorker
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        const val DATABASE_NAME = "wallet.db"
        const val CURRENCY_PATH = "api/latest.json?app_id=${BuildConfig.CURRENCY_API_KEY}&symbols=RUB,EUR"
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

        startCurrencyWorker()

        Stetho.initializeWithDefaults(this);
    }

    private fun startCurrencyWorker() {

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

        WorkManager.getInstance().getStatusesByTag(CurrencyWorker.TAG).observeForever {
            for (work in it!!) {
                if (!work.state.isFinished) {
                    return@observeForever
                }
            }

            val currencyWorker = PeriodicWorkRequest
                    .Builder(CurrencyWorker::class.java, 12, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .addTag(CurrencyWorker.TAG)
                    .build()

            WorkManager.getInstance().enqueue(currencyWorker)
        }
    }
}
