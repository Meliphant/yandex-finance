package ya.co.yandex_finance.repository.model

import ya.co.yandex_finance.repository.model.utils.Categories
import ya.co.yandex_finance.repository.model.utils.TransactionType
import java.util.*

//todo Parcelable?
data class Transaction(val name: String,
                       val amount: Double,
                       val type: TransactionType,
                       val category: Categories,
                       val wallet: Wallet,
                       val dateTime: Date) /*: Parcelable*/ {

//    constructor(parcel: Parcel) : this(
//            parcel.readString(),
//            parcel.readInt(),
//            TransactionType.values()[parcel.readInt()],
//            Categories.values()[parcel.readInt()],
//            //todo parcel.readInt(),
//            Date(parcel.readLong()))
//
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//        parcel.writeInt(amount)
//        parcel.writeInt(type.ordinal)
//        parcel.writeInt(category.ordinal)
//        //todo parcel.writeInt(walletId)
//        parcel.writeLong(dateTime.time)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Transaction> {
//        override fun createFromParcel(parcel: Parcel): Transaction {
//            return Transaction(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Transaction?> {
//            return arrayOfNulls(size)
//        }
//    }
}
