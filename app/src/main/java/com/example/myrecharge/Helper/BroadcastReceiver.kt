package com.example.myrecharge.Helper

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import de.mateware.snacky.Snacky

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var status: String = NetworkUtil.getConnectivityStatusString(context)!!
        if (status.isEmpty()) {
            status = "No Internet Connection"
        }

        if(status.equals("No internet is available"))
        {
            Snacky.builder()
                .setActivity(context as Activity?)
                .setActionText("Hide")
                .setActionTextColor(Color.parseColor("#ffffff"))
                .setActionTextColor(Color.parseColor("#5D6D7E"))
                .setText(status)
                .setTextColor(Color.parseColor("#ffffff"))
                .setBackgroundColor(Color.parseColor("#041e37"))
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .build()
                .show()
        }
    }
}