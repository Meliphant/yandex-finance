package ya.co.yandex_finance.model.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ya.co.yandex_finance.BuildConfig
import ya.co.yandex_finance.model.entities.DataCurrencyRates

interface CurrencyApi {

    @GET(BuildConfig.CURRENCY_PATH)
    fun loadCurrencies(@Query("app_id") appId: String, @Query("symbols") symbols: String):
            Observable<DataCurrencyRates>
}
