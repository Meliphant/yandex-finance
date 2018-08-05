package ya.co.yandex_finance.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


/**
 * Check if Internet is Available on device
 *
 * @param context of application
 * @return internet status
 */
fun isInternetAvailable(context: Context): Boolean {
    val mConMgr: ConnectivityManager? = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return mConMgr?.activeNetworkInfo != null
            && mConMgr.activeNetworkInfo.isAvailable
            && mConMgr.activeNetworkInfo.isConnected
}
