package com.example.learntime.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.learntime.MainActivity
import com.example.learntime.R


class ForegroundService : Service() {
    private val CHANNEL_ID = "2"
    var player: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.tune);
        player?.isLooping = true; // Set looping
        player?.setVolume(100F, 100F);
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notif = createNotification()
        startForeground(1, notif)
        // do heavy work on a background thread
       startMusic()
        //stopSelf();
        //do heavy work on a background thread
        //stopSelf();
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotification(): Notification {
        // Define an intent that will open when the notification is tapped
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText("Playing music")
            .setSmallIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
            .setContentIntent(pendingIntent)

        return notificationBuilder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun startMusic() {
        if (player?.isPlaying?.not() == true) {
            player?.start()
        }
    }

    override fun onDestroy() {
        Toast.makeText(this, "Foreground service done", Toast.LENGTH_SHORT).show()
        player?.stop();
        player?.release();
    }
}