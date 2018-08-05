package ya.co.yandex_finance.model.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Update
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ya.co.yandex_finance.model.entities.Transaction
import ya.co.yandex_finance.model.entities.TransactionWithWallet

@Dao
interface TransactionDao {

    @Query("SELECT * from transactions")
    fun getAllTransactions(): Flowable<List<Transaction>>

    @Query("SELECT * from transactions WHERE walletId=:walletId")
    fun getAllForWallet(walletId: Int): Flowable<List<Transaction>>

    @Query("SELECT tr.*, w.* from transactions tr inner join wallets w on tr.walletId = w.wId")
    fun getAllTransactionsWithWallet(): Flowable<List<TransactionWithWallet>>

    @Query("SELECT tr.*, w.* from transactions tr inner join wallets w on tr.walletId = w.wId where walletId=:walletId")
    fun getTransactionsWithWallet(walletId: Int): Flowable<List<TransactionWithWallet>>

    @Insert(onConflict = REPLACE)
    fun insert(transaction: Transaction)

    @Insert(onConflict = REPLACE)
    fun insert(transactions: List<Transaction>)
}
