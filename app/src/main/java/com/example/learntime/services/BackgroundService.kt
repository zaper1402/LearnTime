package com.example.learntime.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class BackgroundService : Service() {
    private var runService : Boolean = true

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            while (runService) {
                Log.e("Service", "Background Service is running...")
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        runService = false
        Log.e("Service", "Background Service is stopped...")
        Toast.makeText(this, "Background service done", Toast.LENGTH_SHORT).show()
    }
}