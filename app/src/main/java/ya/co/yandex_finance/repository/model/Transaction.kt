package ya.co.yandex_finance.repository.model

import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.TransactionType
import java.util.Date


data class Transaction(val name: String, val amount: Int, val type: TransactionType, val category: Categories,
                       val wallet: Wallet, val dateTime: Date) {
}