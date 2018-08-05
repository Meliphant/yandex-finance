package ya.co.yandex_finance.model.persistence.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ya.co.yandex_finance.model.entities.Wallet

@Dao
interface WalletDao {

    @Query("SELECT * from wallets")
    fun getAllWallets(): Flowable<List<Wallet>>

    @Query("SELECT * from wallets WHERE wId=:walletId")
    fun getById(walletId: Int): Single<Wallet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wallet: Wallet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wallets: List<Wallet>)

    @Query("DELETE from wallets")
    fun deleteAll()

    @Delete
    fun deleteWallet(wallet: Wallet)

    @Update
    fun update(wallet: Wallet)
}
