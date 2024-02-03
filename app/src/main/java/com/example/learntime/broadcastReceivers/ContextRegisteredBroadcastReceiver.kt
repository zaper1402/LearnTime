package com.example.learntime.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ContextRegisteredBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val data  = intent?.extras?.getString(BroadcastReceiverConstants.DATA_STR)
        Log.d("Context_broadcast","Context broadcast received")
        data?.let{
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        }
    }
}