package ya.co.yandex_finance.model.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionWithWallet
import java.util.*

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions " +
                "ORDER BY tId DESC")
    fun getAllTransactions(): Flowable<List<Transaction>>

    @Query("SELECT * FROM transactions " +
                "WHERE walletId=:walletId " +
                "ORDER BY tId DESC")
    fun getAllTransactionsForWallet(walletId: Int): Flowable<List<Transaction>>

    @Query("SELECT tr.*, w.* FROM transactions tr " +
                    "INNER JOIN wallets w on tr.walletId = w.wId " +
                "ORDER BY tId DESC")
    fun getAllTransactionsWithWalletInfo(): Flowable<List<TransactionWithWallet>>

    @Query("SELECT tr.*, w.* FROM transactions tr " +
                    "INNER JOIN wallets w on tr.walletId = w.wId " +
                "WHERE walletId=:walletId ORDER BY tId DESC")
    fun getTransactionsWithWalletInfoForWallet(walletId: Int): Flowable<List<TransactionWithWallet>>

    @Query("SELECT * FROM transactions " +
                "WHERE dateTime >= :dateTimeStart AND dateTime <= :dateTimeEnd ORDER BY tId DESC")
    fun getTransactionsWithPeriod(dateTimeStart: Date, dateTimeEnd: Date): Flowable<List<Transaction>>

    @Query("SELECT * FROM transactions " +
                "WHERE dateTime >= :dateTimeStart AND dateTime <= :dateTimeEnd AND walletId=:walletId " +
                "ORDER BY tId DESC")
    fun getTransactionsWithPeriodForWallet(dateTimeStart: Date, dateTimeEnd: Date, walletId: Int): Flowable<List<Transaction>>

    @Query("SELECT tr.*, w.* FROM transactions tr " +
                    "INNER JOIN wallets w on tr.walletId = w.wId " +
                "WHERE dateTime >= :dateTimeStart AND dateTime <= :dateTimeEnd " +
                "ORDER BY tId DESC")
    fun getTransactionsWithPeriodWithWalletInfo(dateTimeStart: Date, dateTimeEnd: Date): Flowable<List<TransactionWithWallet>>

    @Query("SELECT tr.*, w.* FROM transactions tr " +
                    "INNER JOIN wallets w on tr.walletId = w.wId " +
                "WHERE dateTime >= :dateTimeStart AND dateTime <= :dateTimeEnd AND walletId=:walletId " +
                "ORDER BY tId DESC")
    fun getTransactionsWithPeriodWithWalletInfoFrWallet(dateTimeStart: Date, dateTimeEnd: Date, walletId: Int): Flowable<List<TransactionWithWallet>>

    @Insert(onConflict = REPLACE)
    fun insert(transaction: Transaction)

    @Insert(onConflict = REPLACE)
    fun insert(transactions: List<Transaction>)
}
