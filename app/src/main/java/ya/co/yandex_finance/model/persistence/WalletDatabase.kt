package ya.co.yandex_finance.model.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import ya.co.yandex_finance.app.App.Companion.DATABASE_NAME
import ya.co.yandex_finance.model.entities.*
import ya.co.yandex_finance.model.entities.Currency
import ya.co.yandex_finance.model.persistence.dao.TransactionDao
import ya.co.yandex_finance.model.persistence.dao.WalletDao
import java.util.*
import java.util.concurrent.Executors


@Database(entities = [Transaction::class, Wallet::class], version = 1, exportSchema = false)
@TypeConverters(value = [TransactionTypeConverters::class, CategoryConverters::class,
    CurrencyConverters::class, WalletTypeConverters::class, DateConverters::class])
abstract class WalletDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun walletDao(): WalletDao

    companion object {

        @Volatile private var INSTANCE: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        WalletDatabase::class.java, DATABASE_NAME)
                        // prepopulate the database after onCreate was called
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    getInstance(context).walletDao().insert(defaultWallets)
                                    //todo remove
                                    getInstance(context).transactionDao().insert(defaultTransactions)
                                }
                            }
                        })
                        .build()

        val defaultWallets = arrayListOf<Wallet>()
        val defaultTransactions = arrayListOf<Transaction>()

        init {
            defaultWallets.add(Wallet(0, "myRubWallet", Currency.RUB, WalletTypes.CASH))
        }
    }
}
