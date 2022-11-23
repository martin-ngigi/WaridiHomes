package com.example.waridihomes.data.util

import android.app.Activity
import android.app.AlertDialog
import com.example.waridihomes.R

class LoadingIndicator(private val mActivity: Activity) {
    private lateinit var  isdialog: AlertDialog

    /**
     * Start Show dialog
     */
    fun  startLoading(){
        /** Set View */
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.progress_bar, null)
        /** set dialog */
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()

    }

    /**
     * dismiss  dialog
     */
    fun isDismiss(){
        isdialog.dismiss()
    }
}