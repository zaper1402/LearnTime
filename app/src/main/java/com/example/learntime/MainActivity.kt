package com.example.learntime

import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.learntime.broadcastReceivers.BroadcastReceiverConstants
import com.example.learntime.broadcastReceivers.ContextRegisteredBroadcastReceiver

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    val broadcastReceiver: BroadcastReceiver = ContextRegisteredBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Lifecycle", "$TAG}, onCreate")
        registerReceiver()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "$TAG}, onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "$TAG}, onResume")
        /**lifecycle learning fragments **/
        supportFragmentManager.beginTransaction().add(R.id.fragment_parent,FragmentMain.newInstance()).commit()
    }

    private fun registerReceiver() {
        val filter = IntentFilter(BroadcastReceiverConstants.CONTEXT_REGISTERED_ACTION)
        ContextCompat.registerReceiver(this,broadcastReceiver,filter,ContextCompat.RECEIVER_EXPORTED)
    }

    private fun unregisterReceiver() {
        unregisterReceiver(broadcastReceiver)
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "$TAG}, onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "$TAG}, onStop")
    }

    override fun onDestroy() {
        unregisterReceiver()
        super.onDestroy()
        Log.d("Lifecycle", "$TAG}, onDestroy")
    }
}