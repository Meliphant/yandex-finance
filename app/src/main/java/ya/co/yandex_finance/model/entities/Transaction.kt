package ya.co.yandex_finance.model.entities

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import java.util.*

@Entity(tableName = "transactions",
        foreignKeys = [(ForeignKey(
                entity = Wallet::class,
                parentColumns = ["wId"],
                childColumns = ["walletId"],
                onDelete = CASCADE))],
                indices=[Index(value=["walletId"])])
data class Transaction(@PrimaryKey(autoGenerate = true) var tId: Int,
                       val description: String,
                       val amount: Double,
                       val type: TransactionType,
                       val category: Categories,
                       val walletId: Int,
                       val dateTime: Date)
