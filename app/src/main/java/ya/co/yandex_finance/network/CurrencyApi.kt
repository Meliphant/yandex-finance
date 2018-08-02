package ya.co.yandex_finance.network

import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {

    @GET(CURRENCY_PATH)
    fun loadCurrencies(): Observable<DataCurrencyRates>
}
