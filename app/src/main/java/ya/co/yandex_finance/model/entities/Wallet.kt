package ya.co.yandex_finance.model.entities

//todo: add transaction list for every wallet & add wallet icon(?)
data class Wallet(val id: Int,
                  val name: String,
                  val currency: Currency,
                  val walletType: WalletTypes)
