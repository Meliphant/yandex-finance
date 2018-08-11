package ya.co.yandex_finance.model.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import ya.co.yandex_finance.BuildConfig
import ya.co.yandex_finance.R
import ya.co.yandex_finance.model.entities.*
import ya.co.yandex_finance.model.persistence.dao.TransactionDao
import ya.co.yandex_finance.model.persistence.dao.TransactionRecurrentDao
import ya.co.yandex_finance.model.persistence.dao.WalletDao
import java.util.concurrent.Executors


@Database(entities = [Transaction::class, Wallet::class, TransactionRecurrent::class], version = 1, exportSchema = false)
@TypeConverters(value = [TransactionTypeConverters::class, CategoryConverters::class,
    CurrencyConverters::class, WalletTypeConverters::class, DateConverters::class])
abstract class WalletDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun transactionRecurrentDao(): TransactionRecurrentDao

    abstract fun walletDao(): WalletDao

    companion object {

        @Volatile
        private var INSTANCE: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        WalletDatabase::class.java, BuildConfig.DATABSE_NAME)
                        //Prepopulate the database after onCreate was called
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    val demoWallet = Wallet(0, context.getString(R.string.demo_wallet_name), 0.0, Currency.USD, WalletTypes.CASH)
                                    getInstance(context).walletDao().insert(demoWallet)
                                }
                            }
                        })
                        .build()
    }
}
