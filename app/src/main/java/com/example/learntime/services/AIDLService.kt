package com.example.learntime.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AIDLService : Service() {

    private val TAG ="AIDLService"
    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG,"on Bind called")
       return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG,"on unBind called")
        return super.onUnbind(intent)
    }

    private val mBinder : IMyAidlInterface.Stub = object : IMyAidlInterface.Stub() {
        override fun addition(x: Int, y: Int): Int {
            return x+y
        }

    }
}