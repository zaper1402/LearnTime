package com.example.learntime.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ManifestDeclaredBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val data  = intent?.extras?.getString(BroadcastReceiverConstants.DATA_STR)
        Log.d("Manifest_broadcast","Manifest broadcast received")
        data?.let{
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()

        }
    }
}