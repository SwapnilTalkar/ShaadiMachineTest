package com.shaadi.invitation.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isNetworkAvailable())
            throw NoInternetConnectivityException("Please check if you have an active internet connection")

        return chain.proceed(chain.request())
    }

    private fun isNetworkAvailable() : Boolean{

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities=connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities?.run {
                return hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        }
        return false
    }

}