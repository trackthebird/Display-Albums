package com.trackthebird.displayalbum.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Helper {

    /**
     * Function Checks Internet connectivity
     */
    fun Context.isNetworkAvailable(): Boolean {
        val connection = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connection.getNetworkCapabilities(connection.activeNetwork)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        } else {
            connection.activeNetworkInfo?.run {
                when (type) {
                    ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE -> return true
                    else -> return false
                }
            }
        }
        return false
    }

    /**
     * Returns formatted String using Extension function
     */
    fun String.getFormattedString(value: String): String {
        return format(value)
    }
}