package ya.co.yandex_finance.app.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ya.co.yandex_finance.BuildConfig
import ya.co.yandex_finance.model.network.CurrencyApi
import ya.co.yandex_finance.model.network.CurrencyService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesCurrencyRepository(currencyApi: CurrencyApi) = CurrencyService(currencyApi)

    @Singleton
    @Provides
    fun providesCurrencyApi(): CurrencyApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.CURRENCY_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(CurrencyApi::class.java)
    }
}
