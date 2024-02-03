package com.example.learntime

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.learntime.broadcastReceivers.BroadcastReceiverConstants
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContextBroadcastTest {

    @Test
    fun testMyBroadcastReceiver() {
        val intent = Intent(BroadcastReceiverConstants.CONTEXT_REGISTERED_ACTION)
        intent.putExtra(BroadcastReceiverConstants.DATA_STR, "COntext registered broadcast sent")

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appContext.sendBroadcast(intent)

    }
}