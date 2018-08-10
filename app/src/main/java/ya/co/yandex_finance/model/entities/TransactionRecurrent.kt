package ya.co.yandex_finance.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "transactions_recurrent",
        foreignKeys = [(ForeignKey(
                entity = Wallet::class,
                parentColumns = ["wId"],
                childColumns = ["walletId"],
                onDelete = CASCADE))],
        indices = [Index(value = ["walletId"])])
data class TransactionRecurrent(@PrimaryKey(autoGenerate = true) var trRecId: Int,
                                var description: String,
                                var amount: Double,
                                var type: TransactionType,
                                var category: Categories,
                                var walletId: Int,
                                var dateTime: Date,
                                var period: Int,
                                var lastupdate: Date)
