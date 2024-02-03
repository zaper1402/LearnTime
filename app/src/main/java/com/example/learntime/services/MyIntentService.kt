package com.example.learntime.services

import android.app.IntentService
import android.content.Intent
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        // Perform some background task here
        /** The main diff is in intent service this method performs task in bg unlike other services **/
        Log.d("MyIntentService", "Task in progress")
        try {
            // Perform the task for 5 seconds
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            // Print the stack trace
            // if an interruption occurs
            e.printStackTrace()
        }
        Log.d("MyIntentService", "Task completed")
    }
}
