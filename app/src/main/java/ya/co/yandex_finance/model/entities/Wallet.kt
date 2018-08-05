package ya.co.yandex_finance.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "wallets")
data class Wallet(@PrimaryKey(autoGenerate = true) var wId: Int,
                  var name: String,
                  val currency: Currency,
                  val walletType: WalletTypes)
