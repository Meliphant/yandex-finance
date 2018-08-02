package ya.co.yandex_finance.repository.model

import ya.co.yandex_finance.repository.model.utils.Currency
import ya.co.yandex_finance.repository.model.utils.WalletTypes

//todo: add transaction list for every wallet & add wallet icon(?)
data class Wallet(val id: Int,
                  val name: String,
                  val currency: Currency,
                  val walletType: WalletTypes)
