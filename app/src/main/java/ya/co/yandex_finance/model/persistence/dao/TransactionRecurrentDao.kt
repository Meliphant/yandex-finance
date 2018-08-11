package ya.co.yandex_finance.model.persistence.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import ya.co.yandex_finance.model.entities.TransactionRecurrent
import ya.co.yandex_finance.model.entities.TransactionRecurrentWithWallet

@Dao
interface TransactionRecurrentDao {

    @Query("SELECT * FROM transactions_recurrent ORDER BY trRecId DESC")
    fun getAllRecurrentTransactions(): Flowable<List<TransactionRecurrent>>

    @Query("SELECT * FROM transactions_recurrent WHERE trRecId=:trRecId")
    fun getTransactionsRecurrentById(trRecId: Int): Flowable<TransactionRecurrent>

    @Query("SELECT * FROM transactions_recurrent WHERE walletId=:walletId ORDER BY trRecId DESC")
    fun getAllRecurrentForWallet(walletId: Int): Flowable<List<TransactionRecurrent>>

    @Query("SELECT tr.*, w.* FROM transactions_recurrent tr INNER JOIN wallets w on tr.walletId = w.wId ORDER BY trRecId DESC")
    fun getAllRecurrentTransactionsWithWallet(): Flowable<List<TransactionRecurrentWithWallet>>

    @Query("SELECT tr.*, w.* FROM transactions_recurrent tr INNER JOIN wallets w on tr.walletId = w.wId WHERE walletId=:walletId ORDER BY trRecId DESC")
    fun getTransactionsRecurrentWithWallet(walletId: Int): Flowable<List<TransactionRecurrentWithWallet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactionRecurrent: TransactionRecurrent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactionRecurrent: List<TransactionRecurrent>)

    @Query("DELETE FROM transactions_recurrent")
    fun deleteAll()

    @Query("DELETE FROM transactions_recurrent WHERE trRecId=:id")
    fun deleteById(id: Int)

    @Update
    fun update(transactionRecurrent: TransactionRecurrent)
}
