package com.booknara.android.apps.patterns.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.booknara.android.apps.background.WorkerHandlerThread
import com.booknara.android.apps.patterns.R
import java.util.concurrent.TimeUnit


class HandlerThreadActivity: AppCompatActivity() {
    private val uiHandler = Handler()
    private lateinit var workerHandlerThread: WorkerHandlerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_view)
        setSupportActionBar(toolbar)

        workerHandlerThread = WorkerHandlerThread("WorkerHandlerThread")
        val task = Runnable {
            for (i in 0..3) {
                try {
                    TimeUnit.SECONDS.sleep(2)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                if (i == 2) {
                    uiHandler.post(Runnable {
                        Log.i(TAG, "running")
                        Toast.makeText(this@HandlerThreadActivity,
                                "I am at the middle of background task",
                                Toast.LENGTH_LONG)
                                .show()
                    })
                }
            }

            uiHandler.post(Runnable {
                Log.i(TAG, "completed")
                Toast.makeText(this@HandlerThreadActivity,
                        "Background task is completed",
                        Toast.LENGTH_LONG)
                        .show()
            })
        }

        workerHandlerThread.start()
        workerHandlerThread.prepareHandler()
        workerHandlerThread.postTask(task)
        workerHandlerThread.postTask(task)
    }

    override fun onDestroy() {
        // Attention: to stop started thread in HandlerThread
        workerHandlerThread.quit()
        super.onDestroy()
    }

    companion object {
        const val TAG = "HandlerActivity"
    }
}