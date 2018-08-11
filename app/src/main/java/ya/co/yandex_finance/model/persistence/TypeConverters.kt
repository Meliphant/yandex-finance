package ya.co.yandex_finance.model.persistence

import android.arch.persistence.room.TypeConverter
import ya.co.yandex_finance.model.entities.Categories
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.entities.TransactionType
import ya.co.yandex_finance.model.entities.WalletTypes
import java.util.*

class TransactionTypeConverters {

    @TypeConverter
    fun typeToInt(transactionType: TransactionType) = transactionType.ordinal

    @TypeConverter
    fun intToType(id: Int) = TransactionType.values()[id]
}

class CategoryConverters {
    @TypeConverter
    fun typeToInt(category: Categories) = category.ordinal

    @TypeConverter
    fun intToType(id: Int) = Categories.values()[id]
}

class CurrencyConverters {
    @TypeConverter
    fun typeToInt(currency: Currency) = currency.ordinal

    @TypeConverter
    fun intToType(id: Int) = Currency.values()[id]
}

class WalletTypeConverters {
    @TypeConverter
    fun typeToInt(walletType: WalletTypes) = walletType.ordinal

    @TypeConverter
    fun intToType(id: Int) = WalletTypes.values()[id]
}

class DateConverters {
    @TypeConverter
    fun dateToLong(date: Date) = date.time

    @TypeConverter
    fun longToDate(longTime: Long) = Date(longTime)
}
