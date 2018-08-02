package ya.co.yandex_finance.model.entities

import java.util.*

data class Transaction(val name: String,
                       val amount: Double,
                       val type: TransactionType,
                       val category: Categories,
                       val wallet: Wallet,
                       val dateTime: Date)
