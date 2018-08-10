package ya.co.yandex_finance.util

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun applySchedulers(subscribeScheduler: Scheduler,
                    observeScheduler: Scheduler): CompletableTransformer {
    return CompletableTransformer { upstream ->
        upstream
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
    }
}

fun <T> applySchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer { flowable ->
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
