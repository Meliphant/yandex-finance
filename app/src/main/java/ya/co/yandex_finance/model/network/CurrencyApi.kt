package ya.co.yandex_finance.model.network

import io.reactivex.Observable
import retrofit2.http.GET
import ya.co.yandex_finance.app.App.Companion.CURRENCY_PATH
import ya.co.yandex_finance.model.entities.DataCurrencyRates

interface CurrencyApi {

    @GET(CURRENCY_PATH)
    fun loadCurrencies(): Observable<DataCurrencyRates>
}
