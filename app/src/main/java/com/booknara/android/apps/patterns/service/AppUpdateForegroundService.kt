package com.booknara.android.apps.patterns.service

import android.app.*
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.booknara.android.apps.patterns.NotificationChannel.APP_UPDATE_CHANNEL_ID
import com.booknara.android.apps.patterns.R
import com.booknara.android.apps.patterns.activity.ForegroundServiceActivity


class AppUpdateForegroundService: Service() {
    private var mHandler: Handler? = null
    private var mRunnable: Runnable? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "AppUpdateForegroundService created")

        val handlerThread = HandlerThread(TAG)
        handlerThread.start()
        mHandler = Handler(handlerThread.looper)

        mRunnable = Runnable {
            while (!Thread.currentThread().isInterrupted) {
                // continue processing
                for (i in 0..300) {
                    Log.i(TAG, "Running service " + (i + 1) + "/5 @ " + SystemClock.elapsedRealtime())
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        // good practice
                        Thread.currentThread().interrupt()
                    }
                }
            }

            Log.i(TAG, "Completed service @ " + SystemClock.elapsedRealtime())
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            stopSelf()
            return START_NOT_STICKY
        }
        val action = intent.action
        Log.i(TAG, "using an intent with action $action")
        val input = intent.getStringExtra("inputExtra")
        createNotificationChannel()

        val notificationIntent = Intent(this, ForegroundServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0)

        val notification: Notification = NotificationCompat.Builder(this, APP_UPDATE_CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build()

        startForeground(NOTIFICATION_ID, notification)

        // do heavy work
        mHandler?.post(mRunnable)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        stopService()
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }

    private fun stopService() {
        // TODO : Stop the running thread
        mHandler?.removeCallbacksAndMessages(mRunnable)
        stopForeground(true)
        stopSelf()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                    APP_UPDATE_CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    // Helper for showing tests
    fun toast(text: CharSequence) {
        mHandler?.post { Toast.makeText(this@AppUpdateForegroundService, text, Toast.LENGTH_SHORT).show() }
    }

    companion object {
        const val TAG = "AppUpdateForegroundService"

        /**
         * The identifier for the notification displayed for the foreground service.
         */
        private const val NOTIFICATION_ID = 12345678
    }
}