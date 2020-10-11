package com.shaadi.invitation.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity(){

    fun showSnackBar(view: View, message: String, isShort: Boolean){

        if (isShort)
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
        else
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}