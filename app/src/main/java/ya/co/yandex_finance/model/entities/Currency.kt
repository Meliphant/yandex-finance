package ya.co.yandex_finance.model.entities

import ya.co.yandex_finance.R

enum class Currency(val signResourceId: Int) {
    USD(R.string.usd_balance_sign),
    RUB(R.string.rub_balance_sign),
    EUR(R.string.eur_balance_sign)
}
