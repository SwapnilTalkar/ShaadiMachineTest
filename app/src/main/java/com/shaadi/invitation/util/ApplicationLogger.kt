package com.shaadi.invitation.util

import android.util.Log

object ApplicationLogger {

    fun showLog(tag: String, message: String){
        Log.e(tag, message)
    }
}