package com.kp.androidworldcities.utilities

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.kp.androidworldcities.R

class AlertDialogUtilities {
    companion object{
        fun showDialog(context: Context){
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle(context.getString(R.string.error_title))
            alertDialog.setMessage(context.getString(R.string.error_description))
            alertDialog.setButton(
                AlertDialog.BUTTON_NEUTRAL, context.getString(R.string.ok))
            { dialog, which -> dialog.dismiss() }
            alertDialog.show()
        }
    }
}