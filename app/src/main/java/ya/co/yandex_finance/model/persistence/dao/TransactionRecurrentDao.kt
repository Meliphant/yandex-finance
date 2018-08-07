//package ya.co.yandex_finance.model.persistence.dao
//
//import android.arch.persistence.room.*
//import io.reactivex.Flowable
//import ya.co.yandex_finance.model.entities.TransactionRecurrent
//import ya.co.yandex_finance.model.entities.TransactionWithWallet
//
//@Dao
//interface TransactionRecurrentDao {
//    @Query("SELECT * from transactions_recurrent")
//    fun getAllRecurrentTransactions(): Flowable<List<TransactionRecurrent>>
////
////    @Query("SELECT * from transactions_recurrent WHERE walletId=:walletId")
////    fun getAllRecurrentTransactionsForWallet(walletId: Int): Flowable<List<TransactionRecurrent>>
////
////    @Query("SELECT tr.*, w.* from transactions_recurrent tr inner join wallets w on tr.walletId = w.wId")
////    fun getAllRecurrentTransactionsWithWallet(): Flowable<List<TransactionWithWallet>>
////
////    @Query("SELECT tr.*, w.* from transactions_recurrent tr inner join wallets w on tr.walletId = w.wId where walletId=:walletId")
////    fun getRecurrentTransactionsWithWallet(walletId: Int): Flowable<List<TransactionWithWallet>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(transactionRecurrent: TransactionRecurrent)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(transactionRecurrent: List<TransactionRecurrent>)
//
//    @Query("DELETE from transactions_recurrent")
//    fun deleteAll()
//
//    @Query("DELETE from transactions_recurrent where trRecId=:id")
//    fun deleteById(id: Int)
//
//    @Update
//    fun update(transactionRecurrent: TransactionRecurrent)
//}