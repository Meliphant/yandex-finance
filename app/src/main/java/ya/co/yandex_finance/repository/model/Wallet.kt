package ya.co.yandex_finance.repository.model

import ya.co.yandex_finance.repository.model.utils.MyCurrency
import ya.co.yandex_finance.repository.model.utils.WalletTypes

data class Wallet(val name: String, val currency: MyCurrency, val walletType: WalletTypes) {
}